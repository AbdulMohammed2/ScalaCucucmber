#!/bin/bash
#sbt -Dbrowser=firefox -Denvironment=local -Djava.io.tmpdir 'test-only uk.anatwine.automation.utils.AnatTestRunner'
sbt -Denvironment=local -Djava.io.tmpdir 'test-only uk.anatwine.automation.utils.AnatTestRunner'