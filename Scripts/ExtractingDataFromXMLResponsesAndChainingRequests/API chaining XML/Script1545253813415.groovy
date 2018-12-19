import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

response1 = WS.sendRequest(findTestObject('countryService/SOAP/ListOfCountryNamesByName'))

String xml1 = response1.responseBodyContent
def dataValue = new XmlSlurper().parseText(xml1)
def countryCode = dataValue.ListOfCountryNamesByNameResponse.ListOfCountryNamesByNameResult.tCountryCodeAndName.tCountryCodeAndName[0].sISOCode.text()

println("Variable global: "+countryCode)

GlobalVariable.countryISOCode = countryCode
WS.sendRequest(findTestObject('countryService/SOAP/CapitalCity', [('countryISOCode') : GlobalVariable.countryISOCode]))

