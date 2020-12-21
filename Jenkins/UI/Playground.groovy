pipeline {
    agent any
    options {
        ansiColor('xterm')
    }
    parameters {
        string(defaultValue: "develop", description: 'Branch to use', name: 'branch_to_build')
        string(defaultValue: "12.3", description: 'XCode version to use (11.4.1/12.2/12.3)', name: 'xcode_version')
        string(defaultValue: "", description: 'Copy Carthage folder from cache? Enter "Yes" to enable (Notice: it builds faster, but may cause issues!)', name: 'cache_carthage')
    }

    stages {
        stage ("Trigger build") {
            steps {
                build(
                    job: 'client-ios-build-pipeline', 
                    parameters: [
                        string(name: 'cache_carthage', value: cache_carthage), 
                        string(name: 'xcode_version', value: xcode_version), 
                        string(name: 'branch_to_build', value: branch_to_build), 
                        string(name: 'BUILD_TYPE', value: 'Playground'), 
                        string(name: 'build_number_override', value: env.BUILD_NUMBER)
                    ]
                )
            }
        }
    }
}

