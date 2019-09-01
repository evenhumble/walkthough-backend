pipeline {
     stages{
        stage("checkout"){

        }
        stage('Build'){
            steps {
                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }
        stage('Compile'){
            steps {
                sh 'mvn clean compile -Dmaven.test.skip=true'
            }
        }
    }
}