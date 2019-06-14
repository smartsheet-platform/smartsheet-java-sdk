#!/bin/sh

install_packages() {
  pip install gitchangelog mako
}

set -e
export RELEASE_TAG=${deploy}
install_packages
echo "beginning deploy..."