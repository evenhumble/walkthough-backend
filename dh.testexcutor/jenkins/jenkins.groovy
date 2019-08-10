#!groovy

node {
    stage('Git checkout'){
        git '<git_url>'
    }
    stage('smoke'){
        try{
            sh '<build_command>'
        }catch (err){
            println err
        }finally {
            publciHTML (target:[
                    reportDir: 'target/site/<location>',
                    reportFiles:'index.html',
                    reportName:'smoke test report'
            ])
        }
    }

    stage('API-Integration'){
        try{
            sh '<build_command>'
        }catch (err){
            println err
        }finally {
            publciHTML (target:[
                    reportDir: 'target/site/<location>',
                    reportFiles:'index.html',
                    reportName:'api test report'
            ])
        }
    }
    stage('UI'){
        try{
            sh '<build_command>'
        }catch (err){
            println err
        }finally {
            publciHTML (target:[
                    reportDir: 'target/site/<location>',
                    reportFiles:'index.html',
                    reportName:'UI test report'
            ])
        }
    }
    stage('Result'){
        junit '<junit report>'
    }
}