#!/bin/sh

set -ev

git clone https://github.com/smartsheet-platform/smartsheet-sdk-tests.git
smartsheet-sdk-tests/travis_scripts/install_wiremock.sh

mvn install -DskipTests -Dgpg.skip

mvn test -B jacoco:report
mvn integration-test jacoco:report
smartsheet-sdk-tests/travis_scripts/start_wiremock.sh
mvn test -Dtest=com.smartsheet.api.sdk_test.* jacoco:report

mvn coveralls:report