pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "nouhaattafi/my-java-app:latest"
        MINIKUBE_IP = "192.168.49.2"
        SONAR_PROJECT_KEY = "my-java-app"
        SONAR_HOST_URL = "http://localhost:9000"
        SONAR_LOGIN = credentials('sonar') // token SonarQube
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/nouha-attafi/Springbootbackend.git'
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

                    # Mettre à jour l'image Docker de l'application
                    kubectl set image deployment/tpcafe-app tpcafe-app=${DOCKER_IMAGE} -n devops

                    # Redémarrer le déploiement
                    kubectl rollout restart deployment/tpcafe-app -n devops
                    kubectl rollout status deployment/tpcafe-app -n devops --timeout=5m
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
            echo '✅ Pipeline terminé avec succès : déploiement + analyse SonarQube !'
        }
        failure {
            echo '❌ Pipeline échoué !'
        }
    }
}
