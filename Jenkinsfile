pipeline {    agent any

    environment {
        DOCKER_IMAGE = "nouhaattafi/my-java-app:latest"
        MINIKUBE_IP = "192.168.49.2"
        SONAR_PROJECT_KEY = "my-java-app"
        SONAR_HOST_URL = "http://localhost:9000"
        SONAR_LOGIN = credentials('nouhe') // token SonarQube
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'backend', url: 'https://github.com/nouha-attafi/Springbootbackend.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') { // nom du serveur SonarQube configuré dans Jenkins
                    sh "mvn sonar:sonar -Dsonar.projectKey=${SONAR_PROJECT_KEY} -Dsonar.host.url=${SONAR_HOST_URL} -Dsonar.login=${SONAR_LOGIN}"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    # Appliquer les déploiements
                    kubectl apply -f mysql-deployment.yaml
                    kubectl apply -f spring-deployment.yaml
pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = "nouhaattafi/my-java-app:latest"
        MINIKUBE_IP = "192.168.49.2"
        SONAR_PROJECT_KEY = "my-java-app"
        SONAR_HOST_URL = "http://localhost:9000"
        SONAR_LOGIN = credentials('nouhe') // Token SonarQube
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
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
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
                sh """
                    docker build -t ${DOCKER_IMAGE} .
                    docker push ${DOCKER_IMAGE}
                """
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    # Appliquer les déploiements
                    kubectl apply -f mysql-deployment.yaml
                    kubectl apply -f spring-deployment.yaml

                    # Mettre à jour l'image Docker
                    kubectl set image deployment/my-java-app \
                        my-java-app=${DOCKER_IMAGE} -n devops

                    # Redémarrer le déploiement
                    kubectl rollout restart deployment/my-java-app -n devops
                    kubectl rollout status deployment/my-java-app -n devops --timeout=5m
                """
            }
        }

        stage('Verify Deployment') {
            steps {
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
            echo '✅ Pipeline terminé avec succès : déploiement + analyse SonarQube OK'
        }
        failure {
            echo '❌ Pipeline échoué !'
        }
    }
}
