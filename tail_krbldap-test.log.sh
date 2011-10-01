#!/bin/sh
tail -f target/krbldap-test.log | fgrep -C 4 --line-buffered pl.org.olo.

