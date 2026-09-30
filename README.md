# jenkins-shared-lib-sample

A sample Jenkins Shared Library repo used in the sample and demo projects

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
