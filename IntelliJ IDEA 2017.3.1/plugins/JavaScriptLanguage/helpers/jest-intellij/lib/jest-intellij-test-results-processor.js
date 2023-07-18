var Tree = require('../../base-test-reporter/intellij-tree')
  , stringifier = require('../../base-test-reporter/intellij-stringifier')
  , util = require('../../base-test-reporter/intellij-util')
  , processStdoutWrite = process.stdout.write.bind(process.stdout)
  , path = require('path');

var tree = new Tree(null, processStdoutWrite);
tree.startNotify();

module.exports = function (result) {
  result.testResults.forEach(function (testResults) {
    processResults(testResults);
  });
  tree.testingFinished();
  return result;
};

function processResults(testResultsPerTestFile) {
  var testFilePath = testResultsPerTestFile.testFilePath;
  var testResults = testResultsPerTestFile.testResults;
  if (typeof testResultsPerTestFile.failureMessage === 'string' && !(Array.isArray(testResults) && testResults.length > 0)) {
    (function () {
      var testFileNode = tree.root.addTestChild(calcPresentableTestFileName(testFilePath), 'file', testFilePath);
      testFileNode.start();
      testFileNode.setOutcome(Tree.TestOutcome.ERROR, null, testResultsPerTestFile.failureMessage, null, null, null, null, null);
      testFileNode.finish(false);
    })();
    return;
  }
  var testFileNode = tree.root.addTestSuiteChild(calcPresentableTestFileName(testFilePath), 'file', testFilePath);
  testFileNode.register();
  testFileNode.start();

  testResults.forEach(function (testResult) {
    var outcome = getOutcome(testResult.status);
    if (outcome === Tree.TestOutcome.SKIPPED) {
      return;
    }
    var currentParentNode = testFileNode;
    testResult.ancestorTitles.forEach(function (suiteTitle) {
      var childSuiteNode = currentParentNode.findChildNodeByName(suiteTitle);
      if (!childSuiteNode) {
        var suiteLocationPath = getLocationPath(currentParentNode, suiteTitle, testFileNode, testFilePath);
        childSuiteNode = currentParentNode.addTestSuiteChild(suiteTitle, 'suite', suiteLocationPath);
        childSuiteNode.start();
      }
      currentParentNode = childSuiteNode;
    });
    var testLocationPath = getLocationPath(currentParentNode, testResult.title, testFileNode, testFilePath);
    var specNode = currentParentNode.addTestChild(testResult.title, 'test', testLocationPath);
    var failureMessage, failureDetails;
    if (testResult.failureMessages.length > 0) {
      failureMessage = '';
      failureDetails = testResult.failureMessages[0];
    }
    if (typeof failureDetails === 'string' && failureDetails.indexOf(').toMatchSnapshot(') >= 0) {
      specNode.setMetainfo('unmatched_snapshot');
    }
    specNode.start();
    specNode.setOutcome(outcome, testResult.duration, failureMessage, failureDetails, null, null, null, null);
    specNode.finish(false);
  });
  testFileNode.children.forEach(function (childNode) {
    childNode.finishIfStarted();
  });
  testFileNode.finish(false);
}

function calcPresentableTestFileName(testPath) {
  return path.basename(testPath);
}

/**
 * @param {TestSuiteNode} parentNode
 * @param {string} nodeName
 * @param {TestSuiteNode} testFileNode
 * @param {string} testFilePath
 * @static
 */
function getLocationPath(parentNode, nodeName, testFileNode, testFilePath) {
  var names = [nodeName], n = parentNode;
  while (n !== testFileNode) {
    names.push(n.name);
    n = n.parent;
  }
  names.push(testFilePath || '');
  names.reverse();
  return util.joinList(names, 0, names.length, '.');
}

/**
 * @param {string} status
 * @returns {TestOutcome}
 */
function getOutcome(status) {
  if (status === 'passed') {
    return Tree.TestOutcome.SUCCESS;
  }
  if (status === 'pending' || status === 'disabled') {
    return Tree.TestOutcome.SKIPPED;
  }
  return Tree.TestOutcome.FAILED;
}
