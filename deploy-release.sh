#!/bin/sh

mvn -Prelease clean verify javadoc:jar source:jar deploy
