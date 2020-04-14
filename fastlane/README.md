fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

Install _fastlane_ using
```
[sudo] gem install fastlane -NV
```
or alternatively using `brew cask install fastlane`

# Available Actions
## Android
### android config
```
fastlane android config
```
Output configuration for debugging
### android compile
```
fastlane android compile
```
Compile debug and test sources
### android lint
```
fastlane android lint
```
Execute Android lint
### android assemble
```
fastlane android assemble
```
Assemble source and test APKs
### android unit_test
```
fastlane android unit_test
```
Execute unit test
### android pipeline
```
fastlane android pipeline
```
Local pipeline execution

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
