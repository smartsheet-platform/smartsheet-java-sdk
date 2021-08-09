#!/bin/sh

install_packages() {
  pip install gitchangelog mako
}

set -e
export RELEASE_TAG=${deploy}

echo $GPG_SECRET_KEYS | base64 --decode | gpg --import
echo $GPG_OWNERTRUST | base64 --decode | gpg --import-ownertrust

install_packages
echo "beginning deploy..."

# git configuration
git config --global user.email "travis@travis-ci.org"
git config --global user.name "Automated Build"

echo "updating release tag..."
git tag ${RELEASE_TAG} -m "Release ${RELEASE_TAG}"
git push https://${GH_USER}:${GH_ACCESS_TOKEN}@github.com/smartsheet-platform/smartsheet-java-sdk.git \
    HEAD:${TRAVIS_BRANCH} --tags > /dev/null 2>&1

echo "beginning build..."
mvn install --settings .maven.xml -DskipTests -Dgpg.skip -B
echo "set release tag to ${RELEASE_TAG}..."
mvn versions:set -DgenerateBackupPoms=false -DnewVersion=${RELEASE_TAG}
echo "beginning publish..."
mvn clean deploy --settings .maven.xml -DskipTests -B

echo "updating changelog..."
gitchangelog
./add-gtm.bash
git add CHANGELOG.md
git add docs/*
git commit -am "chg: doc: build ${RELEASE_TAG}"
git push https://${GH_USER}:${GH_ACCESS_TOKEN}@github.com/smartsheet-platform/smartsheet-java-sdk.git \
    HEAD:${TRAVIS_BRANCH} > /dev/null 2>&1