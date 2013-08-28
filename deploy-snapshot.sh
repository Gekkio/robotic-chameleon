#!/bin/sh

mvn clean verify javadoc:jar source:jar deploy
