#!/bin/sh

cd `dirname $0`/..

scripts/run-tool.sh com.opengamma.integration.tool.portfolio.PortfolioLoaderTool -c config/toolcontext/toolcontext-example.properties -l com/opengamma/util/test/warn-logback.xml $@
