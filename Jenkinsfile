@Library('ceiba-jenkins-library') _
pipeline {
  //Donde se va a ejecutar el Pipeline
  agent {
    label 'Slave_Induccion'
  }

  //Opciones específicas de Pipeline dentro del Pipeline
  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 		disableConcurrentBuilds()
  }

  //Una sección que define las herramientas “preinstaladas” en Jenkins
  tools {
    jdk 'JDK17_Centos' //Versión preinstalada en la Configuración del Master - Modificar según versión de JDK utilizada

  }

  //Aquí comienzan los “ítems” del Pipeline
  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout scm
      }
    }
    
    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Compile & Unit Tests<------------"
        sh 'chmod +x ./microservicio/gradlew'
        //sh './microservicio/gradlew --b ./microservicio/build.gradle clean'
        sh './microservicio/gradlew --b ./microservicio/build.gradle test'


      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:adn-citas-cristian.medina',
        sonarName:'ADN-citas(cristian.medina)',
        sonarPathProperties:'./sonar-project.properties')

      }
    }

    stage('Build') {
      steps {
        echo "------------>Build<------------"
      }
    }  
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
      junit 'build/test-results/test/*.xml'
    }
    failure {
      echo 'This will run only if failed'
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}