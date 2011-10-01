#!/bin/sh
tail -f target/krbldap-test.log | fgrep --line-buffered pl.org.olo.

