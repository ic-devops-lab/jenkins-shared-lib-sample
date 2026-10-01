# jenkins-shared-lib-sample

A sample Jenkins Shared Library repo used in the sample and demo projects

## Connect to a Library

In your Jenkins app:

Manage Jenkins > System > Global Trusted Pipeline Libraries > Add
- Name: "iclabs_sample"
- Default version: main
- Retrieval method: Modern SCM
- Source Code Management: Git
- Project Repository: https://github.com/ic-devops-lab/jenkins-shared-lib-sample
> Save

In the pipeline:

```groovy
@Library('iclabs_sample') _

pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                // Call a custom step from the vars/ directory of your library
                myCustomStep()
            }
        }
    }
}
```
> Note: The underscore `_` at the end of the @Library line is mandatory if the line following it is not an import statement.

## Pipeline steps

### C++ Build step

Source: [`vars/cppBuild.groovy'](./vars/cppBuild.groovy)

Accepts a map of configuration values with a default of an empty map. Possiple config parameters:

|   Parameter   |   Type   | Default Value |
| ------------- | -------- | ------------- |
| buildDir      | String   | 'build'       |
| buildType     | String   | 'Debug'       |
| buildTesting  | Boolean  | true          |

Configures the C++ project from the current (source) directory into a build directory (`buildDir`) using `cmake`, optionally sets generating of the test targets (`buildTesting`), then compiles it using all available cores (`make --build buildDir --parallel`). `buildType` (`DCMAKE_BUILD_TYPE`) chooses the configuration such as `Debug` or `Release`.

---

### C++ CppCheck step

Source: [`vars/cppCheck.groovy'](./vars/cppCheck.groovy)

Accepts a map of configuration values with a default of an empty map. Possiple config parameters:

|   Parameter   |     Type     |     Default Value     |
| ------------- | ------------ | --------------------- |
| sourceDirs    | List[String] | ['src', 'include']    |
| failOnIssue   | Boolean      | false                 |
| reportFile    | String       | 'cppcheck-report.txt' |

Runs cppcheck over the configured source directories (`sourceDirs`) and writes findings to a report file at `reportFile` path.
Interrupts the pipeline with error or proceeds further on issue depending on `failOnIssue`.
`false` means: run static analysis and produce the report, but don't use cppcheck as a blocking quality gate.

---

### C++ ClangFormat step

Source: [`vars/clangFormatCheck.groovy'](./vars/clangFormatCheck.groovy)

Accepts a map of configuration values with a default of an empty map. Possiple config parameters:

|   Parameter   |     Type     |     Default Value     |
| ------------- | ------------ | --------------------- |
| sourceDirs    | List[String] | ['src', 'include']    |
| extensions    | List[String] | ['cpp', 'hpp']        |

Runs clang-format for the files with the given `extensions` over the configured source directories (`sourceDirs`).

---

### Python Environment Setup step

Source: [`vars/pythonBootstrap.groovy'](./vars/pythonBootstrap.groovy)

Accepts a map of configuration values with a default of an empty map. Possiple config parameters:

|   Parameter   |   Type   |    Default Value    |
| ------------- | -------- | ------------------- |
| requirements  | String   | 'requirements.txt'  |
| venvDir       | String   | '.venv'             |
| pythonCmd     | String   | 'python3'           |

Creates a Python virtual environment in the `venvDir` directory using `pythonCmd` command, and installs the project dependencies, listed in the file at `requirements` path.

---

### Lunux System Info

Source: [`vars/sysInfoLinux.groovy'](./vars/sysInfoLinux.groovy)

Prints Linux system information to console.

---

### With Message

Source" [`vars/withMessage`](./vars/withMessage.groovy)

|   Parameter   |   Type   | Default Value |
| ------------- | -------- | ------------- |
| message       | String   | -             |
| body          | Closure  | -             |

Wraps a closure with 'START: <message>' and 'END: <message>'.

---
