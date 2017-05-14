ps -ef | grep ./build/libs/vote-1.0.0.RELEASE.jar | grep -v grep | awk '{print $2}' | xargs kill
