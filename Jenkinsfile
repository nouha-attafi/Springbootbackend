pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = "nouhaattafi/my-java-app:latest"
        MINIKUBE_IP = "192.168.49.2"
        SONAR_PROJECT_KEY = "my-java-app"
        SONAR_HOST_URL = "http://localhost:9000"
        SONAR_LOGIN = credentials('nouhe')
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'backend', 
                    url: 'https://github.com/nouha-attafi/Springbootbackend.git'
            }
        }
        
        stage('Build Maven') {
            steps {
                echo '🔨 Compilation du projet Maven...'
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                echo '🔍 Analyse SonarQube...'
                withSonarQubeEnv('sonar') {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.login=${SONAR_LOGIN}
                    """
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                echo '🐳 Construction de l\'image Docker...'
                sh """
                    docker build -t ${DOCKER_IMAGE} .
                    docker push ${DOCKER_IMAGE}
                """
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                echo '☸️ Déploiement sur Kubernetes...'
                sh """
                    kubectl apply -f mysql-deployment.yaml
                    kubectl apply -f spring-deployment.yaml
                    kubectl set image deployment/my-java-app my-java-app=${DOCKER_IMAGE} -n devops
                    kubectl rollout restart deployment/my-java-app -n devops
                    kubectl rollout status deployment/my-java-app -n devops --timeout=5m
                """
            }
        }

        stage('Verify Deployment') {
            steps {
                echo '✅ Vérification du déploiement...'
                sh """
                    echo "=== PODS ==="
                    kubectl get pods -n devops
                    echo ""
                    echo "=== SERVICES ==="
                    kubectl get svc -n devops
                    echo ""
                    echo "=== APPLICATION URL ==="
                    echo "http://${MINIKUBE_IP}:30082/actuator/health"
                """
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline terminé avec succès : déploiement et analyse SonarQube OK'
        }
        failure {
            echo '❌ Pipeline échoué !'
        }
        always {
            echo '🔚 Nettoyage...'
            cleanWs()
        }
    }
}
 
             
