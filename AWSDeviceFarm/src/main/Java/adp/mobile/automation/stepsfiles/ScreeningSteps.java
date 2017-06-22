package adp.mobile.automation.stepsfiles;

import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;

import java.util.HashMap;

import adp.mobile.automation.pages.*;
import adp.mobile.automation.utilities.ExcelReaderWriter;
import adp.mobile.automation.utilities.WndowAction;
import adp.mobile.automation.utilities.randomSSNgenrater;
import aws.DeviceFarmTestNG.BaseTempletSteps;
import aws.DeviceFarmTestNG.ScreeningLocators;
import aws.DeviceFarmTestNG.ScreeningPages;

import org.jbehave.core.annotations.Aliases;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;

import java.util.HashMap;


/**
 * Created by shivhara on 11/9/2016.
 */

    public class ScreeningSteps extends BaseTempletSteps {
        private ScreeningPages screeningPages = new ScreeningPages();
        private randomSSNgenrater ssnGenerator = new randomSSNgenrater();
        private ExcelReaderWriter excelReaderWriter = new ExcelReaderWriter();
        private ScreeningLocators screeningLoc = new ScreeningLocators();
     //   private CallInScreeningPages callinPage = new CallInScreeningPages();
      //  private StandAlonePage saScreening = new StandAlonePage();
      //  private WebInWalmartPage webInWalmartpg = new WebInWalmartPage();
        private String SSN = null;
      //  private final EvoXmlSteps evoXmlSteps = new EvoXmlSteps();



   //     @Given("User log in for screening process")
        public void invokeEnvSpecificURLForScreening(@Named("scenarioName") String scenarioName, @Named("storyName") String storyName, @Named("storyDescription") String storyDescription){
            try {
                startTest(storyName,storyDescription);
                verifyTest("User log in for screening process");
                reportMapping.setTestName(storyName);

        //        Login login = new Login();
                WndowAction winAct = new WndowAction();
                cleanExecelData();
                setScenarioName(scenarioName);
                HashMap<String, String> excelData = excelReaderWriter.readxlsxFileDataAsPerCell(loadPropValues("envName"),getScenarioName());

                setExcelData(excelData);
                if(!(scenarioName.equals("Xml-In"))) {
                    if (loadPropValues("envName").equals("fit2")) {
                        String screeningURL = (loadPropValues("applicationUrl"));
                        getDriver().get(screeningURL);
         //               login.select_set_Cookie().click();
                        //login.caps_Fit_Portal_Login().click();
          //              login.FIT2_SCREENING_URL_CLICK().click();

                        winAct.switchtoChildWindow();
                        getDriver().get(getExcelData().get("URL"));
                    }else {
                        if(!(loadPropValues("envName").equals("iat"))){
                            getDriver().get(getExcelData().get("URL"));
                        }else{
                            if(!getExcelData().get("URL").equals("")){
                                getDriver().get(getExcelData().get("URL"));
                            }
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in invokeEnvSpecificURLForScreening page",false);
            }



        }

        public void performUnitPageActions(){
            try {
                selectDropdown(screeningPages.unit_State_Dropdown(),getExcelData().get("UNIT_STATE")); // Select unit state dropdown.
                selectDropdown(screeningPages.unit_City_Dropdown(),getExcelData().get("UNIT_CITY")); // Select unit city dropdown.
                selectDropdown(screeningPages.unit_Location_Dropdown(),getExcelData().get("UNIT_LOCATION")); // Select unit location dropdown.
                sleep(3000);
                screeningPages.unit_Continue_Button().click();  // Click on unit continue button
            } catch (Exception e) {
                verifyTest("Faliure in performUnitPageActions page",false);
                e.printStackTrace();
            }
        }

        public void performContinuePageActions(){
            try {
                screeningPages.welcome_Continue_Button().click();  // Click on Welcome continue button
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performContinuePageActions page",false);
            }
        }

        public void performPersonalInfoPageActions(){
            try {
                SSN = String.valueOf(ssnGenerator.generateSSN());

                getExcelData().put("SSN", SSN);
                getExcelData().put("CSSN", SSN);
                screeningPages.personalInfo_SSN_TxtBox().sendKeys(getExcelData().get("SSN"));  // user input in SSN text field.
                screeningPages.personalInfo_ConfirmSSN_TxtBox().sendKeys(getExcelData().get("CSSN"));  // user input in Confirm SSN text field.
                screeningPages.personalInfo_FirstName_TxtBox().sendKeys(getExcelData().get("FNAME"));  // user input in First Name text field.
                screeningPages.personalInfo_MiddleName_TxtBox().sendKeys(getExcelData().get("MNAME"));  // user input in Middle Name text field.
                screeningPages.personalInfo_LastName_TxtBox().sendKeys(getExcelData().get("LNAME"));  // user input in Last Name text field.
                screeningPages.personalInfo_Telephone_TxtBox().sendKeys(getExcelData().get("TELEPHONE"));  // user input in Telephone text field.
                screeningPages.personalInfo_Email_TxtBox().sendKeys(getExcelData().get("EMAIL"));  // user input in email address text field.
                screeningPages.personalInfo_Continue_Button().click();  // Click on Personal info continue button.
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performPersonalInfoPageActions page",false);
            }
        }

        public void performAgeVerificationPageActions(){
            try {
                if(getExcelData().get("AGE").equals("YES")) {
                    screeningPages.ageVerification_Yes_Button().click();  // Click on Age Verification Yes button.
                    screeningPages.ageVerification_DOB_TextBox().sendKeys(getExcelData().get("DOB"));   // user input in DOB text field.
                    screeningPages.ageVerification_Continue_Button().click();   // Click on Age Verification continue button.
                }else{
                    screeningPages.ageVerification_No_Button().click();   // Click on Age Verification No button.
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performAgeVerificationPageActions page",false);
            }
        }

        public void performAddressPageActions(){
            try {
                screeningPages.address_StreetAddress_TextBox().sendKeys(getExcelData().get("HOME_STREET_ADDRESS"));   // user input in Street Address text field.
                screeningPages.address_City_TextBox().sendKeys(getExcelData().get("CITY"));     // user input in City text field.
                selectDropdownByValue(screeningPages.address_State_Dropdown(),getExcelData().get("STATE"));  // Select state dropdown.
                screeningPages.address_Zip_TextBox().sendKeys(getExcelData().get("ZIP"));  // user input in Zip text field.
                screeningPages.address_Continue_Button().click();   // Click on Address continue button.
                sleep(2000);
                if(isDisplayed(ScreeningLocators.invalidAddressNote)){
                    screeningPages.address_Continue_Button().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performAddressPageActions page",false);
            }
        }

        public void performPreviousEmpPageActions(){
            try {
                if(getExcelData().get("PREVIOUS_EMPLOYEMENT").equals("YES")) {
                    screeningPages.previousEmp_Yes_Button().click();  // Click on previous Employement Yes button.
                }else{
                    screeningPages.previousEmp_No_Button().click();   // Click on previous Employement No button.
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performPreviousEmpPageActions page",false);
            }
        }

        public void performConditionalCertPageActions(){
            try {
                if(getExcelData().get("CONDITIONAL_CERT").equals("YES")) {
                    screeningPages.conditionalCert_Yes_Button().click();  // Click on conditional certification Yes button.
                }else{
                    screeningPages.conditionalCert_No_Button().click();   // Click on conditional certification No button.
                }
                if(getScenarioName().contains("WalmartWebIn"))
                {
                    if(getExcelData().get("CONDITIONAL_CERT_QUE2").equals("YES")) {
        //                webInWalmartpg.walmart_AGE_YES_CCTW_YES().click();  // Click on conditional certification Yes button.
                    }else{
        //                webInWalmartpg.walmart_AGE_NO_CCWTW_NO().click();   // Click on conditional certification No button.
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performConditionalCertPageActions page",false);
            }
        }

        public void performMilitaryServicePageActions(){
            try {
                if(getExcelData().get("Have you served in the U.S military?").equals("YES")) {
                    screeningPages.militaryService_ServedUSMilitary_Yes_Button().click();  // Click on previous Employement Yes button.

                    if(getExcelData().get("Are you entitled to receive monthly payments for a service-connected disability?").equals("YES")){
                        screeningPages.militaryService_VeternDisability_Yes_Button().click();

                        if(getExcelData().get("Were you discharged or released from active duty from the armed forces in the past year?").equals("YES")){
                            screeningPages.militaryService_VeternDischaredReleased_Yes_Button().click();

                            if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                                waitForLoad();
                                selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                screeningPages.militaryService_militryContinueButton_Button().click();
                            }else{
                                if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){
                                    screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                    waitForLoad();
                                    selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                    selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                    screeningPages.militaryService_militryContinueButton_Button().click();

                                }else{
                                    screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                                    waitForLoad();
                                    selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                    selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                    screeningPages.militaryService_militryContinueButton_Button().click();
                                }
                            }
                        }else{
                            screeningPages.militaryService_VeternDischaredReleased_No_Button().click();

                            if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                                waitForLoad();
                                selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                screeningPages.militaryService_militryContinueButton_Button().click();
                            }else{
                                if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){

                                    screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                    waitForLoad();
                                    selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                    selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                    screeningPages.militaryService_militryContinueButton_Button().click();

                                }else{
                                    screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                                }
                            }
                        }
                    }else{
                        if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                            screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                            waitForLoad();
                            selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                            selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                            screeningPages.militaryService_militryContinueButton_Button().click();
                        }else{
                            screeningPages.militaryService_VeternUnemployment4_No_Button().click();
                            if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                waitForLoad();
                                selectDropdown(screeningPages.militaryService_branchOfService_Dropdown(),getExcelData().get("MILTRY_BRANCH_SERVICE"));
                                selectDropdown(screeningPages.militaryService_StateReceiveBenefits_Dropdown(),getExcelData().get("In what State did you receive the benefits?"));
                                screeningPages.militaryService_militryContinueButton_Button().click();

                            }else{
                                screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                            }
                        }
                    }
                }else{
                    screeningPages.militaryService_ServedUSMilitary_No_Button().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performMilitaryServicePageActions page",false);
            }
        }

        public void performMilitaryServiceWalmart(){
            try {
                if(getExcelData().get("Have you served in the U.S military?").equals("YES")) {
                    screeningPages.militaryService_ServedUSMilitary_Yes_Button().click();  // Click on previous Employement Yes button.

                    if(getExcelData().get("Are you entitled to receive monthly payments for a service-connected disability?").equals("YES")){
                        screeningPages.militaryService_VeternDisability_Yes_Button().click();

                        if(getExcelData().get("Were you discharged or released from active duty from the armed forces in the past year?").equals("YES")){
                            screeningPages.militaryService_VeternDischaredReleased_Yes_Button().click();

                            if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                                waitForLoad();
                            }else{
                                if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){
                                    screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                    waitForLoad();
                                }else{
                                    screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                                    waitForLoad();
                                }
                            }
                        }else{
                            screeningPages.militaryService_VeternDischaredReleased_No_Button().click();

                            if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                                waitForLoad();
                            }else{
                                if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){

                                    screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                    waitForLoad();
                                }else{
                                    screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                                }
                            }
                        }
                    }else{
                        if(getExcelData().get("Were you unemployed for at least 4 weeks but less than 6 months in the past year?").equals("YES")){
                            screeningPages.militaryService_VeternUnemployment4_Yes_Button().click();
                            waitForLoad();
                        }else{
                            screeningPages.militaryService_VeternUnemployment4_No_Button().click();
                            if(getExcelData().get("Were you unemployed at least 6 months in the past year?").equals("YES")){
                                screeningPages.militaryService_VeternUnemployment6_Yes_Button().click();
                                waitForLoad();
                            }else{
                                screeningPages.militaryService_VeternUnemployment6_No_Button().click();
                            }
                        }
                    }
                }else{
                    screeningPages.militaryService_ServedUSMilitary_No_Button().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performMilitaryServiceWalmart page",false);
            }
        }

        public void performVeternFoodStampsPageActions(){
            try {
                if(getExcelData().get("Are you a member of a family that has received food stamps anytime since 04/23/2015 (15 months ago)").equals("YES")) {
                    screeningPages.veternFoodStamps_VerternFoodStamps_Yes_Button().click();
                    selectDropdown(screeningPages.veternFoodStamps_BranchService_Dropdown(), getExcelData().get("BRANCH_SERVICE_VETERNAN"));
                    screeningPages.veternFoodStamps_StartDate_TxtBox().sendKeys(getExcelData().get("SERVICE_START_MONTH"));
                    screeningPages.veternFoodStamps_EndDate_TxtBox().sendKeys(getExcelData().get("SERVICE_END_MONTH"));
                    screeningPages.veternFoodStamps_Recipient_TxtBox().sendKeys(getExcelData().get("FOOD_STAMPS_RECIPIENT"));
                    screeningPages.veternFoodStamps_Relation_TxtBox().sendKeys(getExcelData().get("VETERNAN_RELATION"));
                    screeningPages.veternFoodStamps_City_TxtBox().sendKeys(getExcelData().get("VETERN_CITY"));
                    selectDropdown(screeningPages.veternFoodStamps_State_Dropdown(), getExcelData().get("VERTERN_STATE"));
                    screeningPages.veternFoodStamps_Notes_TxtBox().sendKeys(getExcelData().get("VERTERN_NOTES"));
                    screeningPages.veternFoodStamps_Continue_Button().click();
                }else{
                    screeningPages.veternFoodStamps_VerternFoodStamps_No_Button().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performVeternFoodStampsPageActions page",false);
            }
        }

        public void performTemporaryAssistancePageActions(){
            try {
                if(getExcelData().get("TEMP_ASSISTANCE_QUE_A").equals("YES")) {
                    screeningPages.tempAssistance_Que_A_YesButton().click();
                    screeningPages.tempAssistance_Que_B_YesButton().click();
                    screeningPages.tempAssistance_Que_C_YesButton().click();
                    screeningPages.tempAssistance_Recipient_TxtBox().sendKeys(getExcelData().get("PRIMARY_RECIPIENT_NAME"));
                    screeningPages.tempAssistance_Relation_TxtBox().sendKeys(getExcelData().get("TEMP_ASSISTANCE_RELATION"));
                    screeningPages.tempAssistance_City_TxtBox().sendKeys(getExcelData().get("TEMP_ASSISTANCE_CITY"));
                    selectDropdown(screeningPages.tempAssistance_State_Dropdown(), getExcelData().get("TEMP_ASSISTANCE_STATE"));
                    screeningPages.tempAssistance_Continue_Btn().click();
                }else{
                    screeningPages.tempAssistance_Que_A_NoButton().click();
                    screeningPages.tempAssistance_Que_B_NoButton().click();
                    screeningPages.tempAssistance_Que_C_NoButton().click();
                    // screeningPages.tempAssistance_Continue_Btn().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performTemporaryAssistancePageActions page",false);
            }
        }

        public void performVocationalRehabitiationPageActions(){
            try {
                if(getExcelData().get("VOCATIONAL_REHABILIATION_QUE").equals("YES STATE AGENCY")) {
                    screeningPages.vocationalRehab_YesAgency_Btn().click();
                }
                if(getExcelData().get("VOCATIONAL_REHABILIATION_QUE").equals("YES TICKET TO WORK PROGRAM")) {
                    screeningPages.vocationalRehab_YesTW_Btn().click();
                }
                if(getExcelData().get("VOCATIONAL_REHABILIATION_QUE").equals("YES VA")) {
                    screeningPages.vocationalRehab_YesVA_Btn().click();
                }
                if(getExcelData().get("VOCATIONAL_REHABILIATION_QUE").equals("NO")) {
                    screeningPages.vocationalRehab_No_Btn().click();
                }
                if(getScenarioName().contains("WalmartWebIn"))
                {
                    sleep(2000);
                    screeningPages.tempAssistance_Continue_Btn().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performVocationalRehabitiationPageActions page",false);
            }
        }

        public void performSSIPageActions(){
            try {
                waitForLoad();
                if(getExcelData().get("SSI").equals("YES")) {
                    screeningPages.ssi_Yes_Btn().click();
                }else{
                    screeningPages.ssi_No_Btn().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performSSIPageActions page",false);
            }
        }

        public void performFoodStampsPageActions(){
            try {
                if(getExcelData().get("FOOD_STAMPS_QUE").equals("YES")) {
                    screeningPages.foodStamps_Yes_Btn().click();
                    sleep(1000);
                    waitForLoad();
                    screeningPages.foodStamps_Recipient_TxtBox().sendKeys(getExcelData().get("FOOD_STAMPS_RECIPIENT_NAME"));
                    screeningPages.foodStamps_Relation_TxtBox().sendKeys(getExcelData().get("FOOD_STAMPS_RECIPIENT_RELATION"));
                    screeningPages.foodStamps_City_TxtBox().sendKeys(getExcelData().get("FOOD_STAMPS_CITY"));
                    selectDropdown(screeningPages.foodStamps_State_Dropdown(),getExcelData().get("FOOD_STAMPS_STATE"));
                    screeningPages.foodStamps_Continue_Btn().click();
                }else{
                    screeningPages.foodStamps_No_Btn().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performFoodStampsPageActions page",false);
            }
        }

        public void performLongTermUnemplymentPageActions(){
            try {
                if(getExcelData().get("LONG_TERM_UNEMP_QUE_1").equals("YES")) {
                    screeningPages.longTermUnemp_QueA_Yes_Btn().click();

                    if(getExcelData().get("LONG_TERM_UNEMP_QUE_2").equals("YES")){
                        screeningPages.longTermUnemp_QueB_Yes_Btn().click();

                        waitForLoad();
                        screeningPages.longTermUnemp_Benefit_Start_Date().sendKeys(getExcelData().get("LONG_TERM_UNEMP_START_DATE"));
                        selectDropdown(screeningPages.longTermUnemp_State_Dropdown(),getExcelData().get("LONG_TERM_STATE"));
                        screeningPages.longTermUnemp_Continue_Btn().click();
                    }else{
                        screeningPages.longTermUnemp_QueB_No_Btn().click();
                    }
                }else{
                    screeningPages.longTermUnemp_QueA_No_Btn().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performLongTermUnemplymentPageActions page",false);
            }
        }

        public void performEsignPageActions(){
            try {
                if(getExcelData().get("ESIGN").equals("YES")) {
                    screeningPages.eSign_acknowledge_CheckBox().click();
                    screeningPages.eSign_PIN_TxtBox().sendKeys(getExcelData().get("WOTC_PIN"));
                    screeningPages.eSign_Continue_Btn().click();
                }else if (getExcelData().get("ESIGN").equals("NO"))
                {
                    screeningPages.eSign_optout_CheckBox().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performEsignPageActions page",false);
            }
        }

        public void performIECPageActions(){
            try {
                if(getExcelData().get("IEC_QUE_1").equals("YES")) {
                    screeningPages.IEC_FED_IEC_Native_American_YES_TxtBox().click();
                    waitForLoad();
                    screeningPages.IEC_NameOfTribe_TxtBox().sendKeys(getExcelData().get("IEC_NAME_TRIBE"));
                    screeningPages.IEC_CityOfTribe_TxtBox().sendKeys(getExcelData().get("IEC_CITY_TRIBE"));
                    selectDropdown(screeningPages.IEC_StateOfTribe_dropdown(),getExcelData().get("IEC_STATE_TRIBE"));
                    screeningPages.IEC_DOB_TxtBox().sendKeys(getExcelData().get("IEC_DOB"));
                    screeningPages.IEC_Maiden_TxtBox().sendKeys(getExcelData().get("IEC_MAIDEN_NAME"));
                    screeningPages.IEC_Continue_Btn().click();
                }else{
                    screeningPages.IEC_FED_IEC_Native_American_No_TxtBox().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performIECPageActions page",false);
            }
        }

        public void performIECSPPageActions(){
            try {
                if(getExcelData().get("IECSP_QUE_1").equals("YES")) {
                    screeningPages.IEC_SP_FED_Native_Spouse_YES_TxtBox().click();
                    waitForLoad();
                    sleep(1000);
                    screeningPages.IEC_SP_SpouseName_TxtBox().sendKeys(getExcelData().get("IECSP_SPOUSE_NAME"));
                    screeningPages.IEC_SP_SpouseSSN_TxtBox().sendKeys(getExcelData().get("IECSP_SPOUSE_SSN"));
                    screeningPages.IEC_SP_SpouseDOB_TxtBox().sendKeys(getExcelData().get("IECSP_SPOUSE_DOB"));
                    screeningPages.IEC_SP_SpouseMaidenName_TxtBox().sendKeys(getExcelData().get("IECSP_SPOUSE_MAIDEN_NAME"));
                    screeningPages.IEC_SP_NameOfTribe_TxtBox().sendKeys(getExcelData().get("IECSP_TRIBE_NAME"));
                    screeningPages.IEC_SP_CityOfTribe_TxtBox().sendKeys(getExcelData().get("IECSP_TRIBE_CITY"));
                    selectDropdown(screeningPages.IEC_SP_StateOfTribe_Dropdown(),getExcelData().get("IECSP_TRIBE_STATE"));
                    screeningPages.IEC_SP_Continue_Btn().click();
                }else{
                    screeningPages.IEC_SP_FED_Native_Spouse_No_TxtBox().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performIECSPPageActions page",false);
            }
        }

        public void performFileUploadPageActions(){
            try {
                screeningPages.fileUpload_Address_btn().sendKeys(fileUploadScripts+"DocumentUpload_Automation.jpg");
                sleep(3000);
                selectDropdown(screeningPages.fileUpload_Address_SelectBox(),"VOTER CARD");
                screeningPages.fileUpload_Continue_Btn().click();
                waitForLoad();
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performFileUploadPageActions page",false);
            }
        }
        public void getConfirmationNumber(){
            try {
                String confNumber = null;
                confNumber = screeningPages.confirmationPage_ConfirmationNumber_Text().getText();
                confNumber = confNumber.split(":")[1].trim();
                getExcelData().put("Confirmation_Number", confNumber);
                System.out.println("Confirmation_Number : "+getExcelData().get("Confirmation_Number"));
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in getConfirmationNumber page",false);
            }
        }

        public void performFelonyPageActions(){
            try {
                waitForLoad();
                if(getExcelData().get("FELONY_QUE_1").equals("YES")) {
                    screeningPages.Felony_General_YES_BTN().click();
                }else{
                    screeningPages.Felony_General_NO_BTN().click();

                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performFelonyPageActions page",false);
            }
        }

        public void performSSDIPageActions(){
            try {
                waitForLoad();
                if(getExcelData().get("SSDI_QUE_1").equals("YES")) {
                    screeningPages.SSDI_YES_BTN().click();
                }else{
                    screeningPages.SSDI_NO_BTN().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performSSDIPageActions page",false);
            }
        }

        public void philadelphiaMServicePageActions(){
            try {
                waitForLoad();
                if(getExcelData().get("PHIL_QUES_YES_1").equals("YES")) {
                    screeningPages.philadelphia_Que1_YES_BTN().click();
                }else{
                    screeningPages.philadelphia_Que1_NO_BTN().click();
                }
                if(getExcelData().get("PHIL_QUES_YES_2").equals("YES")) {
                    screeningPages.philadelphia_Que2_YES_BTN().click();
                }else{
                    screeningPages.philadelphia_Que2_NO_BTN().click();
                }
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in philadelphiaMServicePageActions page",false);
            }
        }

        public void performHireInfomationPageActions(){
            try {
    		/*screeningPages.hire_Info_Gave_Info_Date().sendKeys(getExcelData().get("HIRE_GAVE_INFO_DATE"));
    		screeningPages.hire_Info_Job_Offer_Date().sendKeys(getExcelData().get("HIRE_JOB_OFFER_DATE"));
    		screeningPages.hire_Info_Hire_Date().sendKeys(getExcelData().get("HIRE_DATE"));
    		screeningPages.hire_Info_Start_Date().sendKeys(getExcelData().get("HIRE_START_DATE"));*/
                screeningPages.hire_Info_Gave_Info_Date().sendKeys(currentdate());
                screeningPages.hire_Info_Job_Offer_Date().sendKeys(currentdate());
                screeningPages.hire_Info_Hire_Date().sendKeys(currentdate());
                screeningPages.hire_Info_Start_Date().sendKeys(currentdate());
                selectDropdown(screeningPages.hire_Info_Job_Position(),getExcelData().get("HIRE_JOB_POSITION"));
                screeningPages.hire_Info_Wage().sendKeys(getExcelData().get("HIRE_WAGE"));
                selectDropdown(screeningPages.hire_Info_Wage_Type(),getExcelData().get("HIRE_WAGE_TYPE"));
                screeningPages.IEC_SP_Continue_Btn().click();
            } catch (Exception e) {
                e.printStackTrace();
                verifyTest("Faliure in performHireInfomationPageActions page",false);
            }
        }

        public void performCAMilitryPageActions()
        {
            try {
                waitForLoad();
                if(getExcelData().get("CA_MILITORY_QUE_1").equals("YES")) {
                    screeningPages.CAMilitrary_QueA_Yes_Btn().click();
                    sleep(2000);
                    screeningPages.personalInfo_Continue_Button().click();

                }else{
                    screeningPages.CAMilitrary_QueA_NO_Btn().click();
                }
                if(getExcelData().get("CA_MILITORY_QUE_2").equals("YES")) {
                    screeningPages.CAMilitrary_QueB_Yes_Btn().click();
                    sleep(3000);
                    screeningPages.personalInfo_Continue_Button().click();

                }else{
                    screeningPages.CAMilitrary_QueB_NO_Btn().click();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performCAMilitryPageActions page",false);
            }
        }

        public  void performCAEarnedIncomePageActions()
        {
            try {
                waitForLoad();
                if(getExcelData().get("CA_EARNED_INCOME_CREDIT_QUE_1").equals("YES")) {
                    screeningPages.CAEarnedIncome_Yes_Btn().click();
                    sleep(2000);
                    screeningPages.personalInfo_Continue_Button().click();

                }else{
                    screeningPages.CAEarnedIncome_NO_Btn().click();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performCAEarnedIncomePageActions page",false);
            }
        }

        public void performCAFelonyPageActions()
        {
            try {
                waitForLoad();
                if(getExcelData().get("CA_FELONY_QUE_1").equals("YES")) {
                    screeningPages.CAFelony_Que_Yes_Btn().click();
                    sleep(2000);
                    screeningPages.personalInfo_Continue_Button().click();
                    sleep(2000);

                }else{
                    screeningPages.CAFelony_Que_No_Btn().click();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performCAFelonyPageActions page",false);
            }
        }

        public void performCAGeneralAssistancePageActions()
        {
            try {
                waitForLoad();
                if(getExcelData().get("CA_GENERAL_ASSISTANCE_QUE_1").equals("YES")) {
                    screeningPages.CA_GA_CalWorksCash_Yes_Btn().click();
                    sleep(2000);


                }else{
                    screeningPages.CA_GA_CalWorksCash_No_Btn().click();
                }
                if(getExcelData().get("CA_GENERAL_ASSISTANCE_QUE_2").equals("YES")) {
                    screeningPages.CA_GeneralAssistance_Yes_Btn().click();
                    sleep(2000);


                }else{
                    screeningPages.CA_GeneralAssistance_No_Btn().click();
                }
                if(getExcelData().get("CA_GENERAL_ASSISTANCE_QUE_3").equals("YES")) {
                    screeningPages.CA_GeneralRelief_Yes_Btn().click();
                    sleep(2000);

                }else{
                    screeningPages.CA_GeneralRelief_No_Btn().click();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performCAGeneralAssistancePageActions page",false);
            }
        }

        public void performCAUnempFullTimeStudentActions()
        {
            try{
                waitForLoad();
                if(getExcelData().get("CA_UNEMPLOYED_6MONTHS").equals("YES")) {
                    screeningPages.CA_Unemployed6Month_Yes_Btn().click();
                    sleep(2000);
                    if(getExcelData().get("CA_SELFEMPLOYED").equals("YES")) {
                        screeningPages.CA_Selfemployed_Yes_Btn().click();
                        sleep(2000);
                    }else{
                        screeningPages.CA_Selfemployed_No_Btn().click();
                    }
                    if(getExcelData().get("CA_FULL_TIME_STUDENT").equals("YES")) {
                        screeningPages.CA_FullTimeStudent_Yes_Btn().click();
                        sleep(2000);
                    }else{
                        screeningPages.CA_FullTimeStudent_No_Btn().click();
                    }
                    if(getExcelData().get("CA_BACHELORS_DEGREE_12MONTHS").equals("YES")) {
                        screeningPages.CA_BachelorsDegree12Mon_Yes_Btn().click();
                        sleep(2000);
                        screeningPages.personalInfo_Continue_Button().click();
                    }else{
                        screeningPages.CA_BachelorsDegree12Mon_No_Btn().click();
                    }
                }else{
                    screeningPages.CA_Unemployed6Month_No_Btn().click();
                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performCAUnempFullTimeStudentActions page",false);

            }

        }

        public void performMilitaryServiceSF_180Actions()
        {
            try {
                waitForLoad();
                selectDropdown(screeningPages.militaryService_SF_ActiveComp_Dropdown(),getExcelData().get("Military_Active_Component"));
                sleep(2000);
                screeningPages.personalInfo_Continue_Button().click();
                sleep(2000);
                selectDropdown(screeningPages.militaryService_SF_ReserveComp_Dropdown(),getExcelData().get("Military_Reserve_Component"));
                sleep(2000);
                selectDropdown(screeningPages.militaryService_SF_NationalGuard_Dropdown(),getExcelData().get("Military_National_Guard"));
                sleep(2000);
                screeningPages.personalInfo_Continue_Button().click();
                sleep(2000);
                screeningPages.personalInfo_Continue_Button().click();

            }
            catch (Exception e)
            {
                e.printStackTrace();
                verifyTest("Faliure in performMilitaryServiceSF_180Actions page",false);
            }
        }

        public void performColoradoWPPageActions(){
            try{
                waitForLoad();
                if(getExcelData().get("CO_FAMILY_RECEIVING_TANF").equals("YES")) {
                    screeningPages.CO_Family_Receiving_TANF_Yes_btn().click();
                    sleep(2000);
                    //screeningPages.CO_TANF_Primary_Receipient_TxtBox().sendKeys();
                    screeningPages.CO_TANF_Primary_Receipient_TxtBox().sendKeys(getExcelData().get("CO_TANF_PRIMARY_RECEIPIENT"));
                    screeningPages.CO_TANF_Primary_Receipient_City_TxtBox().sendKeys(getExcelData().get("CO_TANF_PRIMARY_RECEIPIENT_CITY"));
                    selectDropdown(screeningPages.CO_TANF_Primary_Receipient_State_Dropdown(),getExcelData().get("CO_TANF_PRIMARY_RECEIPIENT_STATE"));
                    sleep(2000);
                    screeningPages.personalInfo_Continue_Button().click();
                }else{
                    screeningPages.CO_Family_Receiving_TANF_NO_btn().click();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                verifyTest("Faliure in performColoradoWPPageActions page",false);
            }
        }

        public void performUTMilitaryPageActions(){
            try {
                if(getExcelData().get("UT_QUES_YES_1").equals("YES")) {
                    screeningPages.UTVetranYesBtn().click();
                }else{
                    screeningPages.UTVetranNoBtn().click();
                }
                if(getExcelData().get("UT_QUES_YES_2").equals("YES")) {
                    screeningPages.UTVetranDischargedYesBtn().click();
                }else{
                    screeningPages.UTVetranDischargedNoBtn().click();
                }
                if(getExcelData().get("UT_QUES_YES_3").equals("YES")) {
                    screeningPages.UTVetranUnEmplYesBtn().click();
                }else{
                    screeningPages.UTVetranUnEmplNoBtn().click();
                }
            }catch (Exception e){
                e.printStackTrace();
                verifyTest("Faliure in performMilitaryServiceSF_180Actions page",false);
            }
        }

        public void performSouthCarolinaPageActions(){
            try{
                waitForLoad();
                if(getExcelData().get("SCFIP_AFDC_TANF_QUES1").equals("YES")) {
                    screeningPages.SCFIP_Family_Receiving_TANF_Yes_btn().click();
                    sleep(2000);
                    //screeningPages.CO_TANF_Primary_Receipient_TxtBox().sendKeys();
                    screeningPages.SCFIP_County_Name_TxtBox().sendKeys(getExcelData().get("SCFIP_COUNTY_NAME"));
                    sleep(2000);
                    screeningPages.personalInfo_Continue_Button().click();
                    sleep(2000);
                }else{
                    screeningPages.SCFIP_Family_Receiving_TANF_No_btn().click();
                    sleep(2000);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                verifyTest("Faliure in performSouthCarolinaFIPPageActions page",false);
            }
        }

        @Given("User perform screening test")
        @Aliases(values={"Perform EVO XML screening"})
        public void verifyPageActons() {

            try{
                boolean flag = false;
                boolean noMethodFlag = false;
                while (true) {
                    String pageToCall = null;
                    waitForLoad();
                    explicitWaitForElementDisappears(screeningLoc.CONTINUE_BUTTON_LOADING, 30);
                    sleep(2000);

                    if (!screeningPages.unitPageTitle().isDisplayed()) {
                        pageToCall = screeningPages.screeningPageTitle().getText();
                    } else {
                        pageToCall = screeningPages.unitPageTitle().getText();
                    }
                    sleep(1000);

                    System.out.println("Screening started for " + pageToCall);

                    switch (pageToCall) {
                        case "Welcome":
                            performContinuePageActions();

                            break;
                        case "Personal Information":
                            if (getScenarioName().contains("Evo-Xml")) {
        //                        evoXmlSteps.verifyPersonalInfoAutoPopulatedFields();
                            } else {
                                if (getScenarioName().contains("WalmartWebIn")) {
         //                           webInWalmartpg.walmartPersonalInformation();
                                } else {
                                    performPersonalInfoPageActions();
                                }
                            }

                            break;
                        case "Age Verification":
                            performAgeVerificationPageActions();

                            break;
                        case "Address":
                            if (getScenarioName().contains("Evo-Xml")) {
       //                         evoXmlSteps.verifyAddressPageAutoPopulatedFields();
                            } else {
                                performAddressPageActions();
                            }

                            break;
                        case "Previous Employment":
                            performPreviousEmpPageActions();

                            break;
                        case "Conditional Certification":
                            performConditionalCertPageActions();

                            break;
                        case "Military Service":
                            if (getScenarioName().contains("WalmartWebIn")) {
                                performMilitaryServiceWalmart();
                            } else {
                                performMilitaryServicePageActions();
                            }
                            break;
                        case "Veteran Food Stamps":
                            performVeternFoodStampsPageActions();

                            break;
                        case "Temporary Assistance":
                            if (getScenarioName().contains("WalmartWebIn")) {
      //                          webInWalmartpg.performTemporaryAssistanceWalmart();
                            } else {
                                performTemporaryAssistancePageActions();
                            }

                            break;
                        case "Vocational Rehabilitation":
                            performVocationalRehabitiationPageActions();

                            break;
                        case "Supplemental Security Income":
                            performSSIPageActions();

                            break;
                        case "Food Stamps":
                            if (getScenarioName().contains("WalmartWebIn")) {
        //                        webInWalmartpg.performFoodStampsPageWalmart();
                            } else {
                                performFoodStampsPageActions();
                            }

                            break;
                        case "Long Term Unemployed":
                            performLongTermUnemplymentPageActions();

                            break;
                        case "WOTC eSign":
                            performEsignPageActions();

                            break;
                        case "IEC":
                            performIECPageActions();

                            break;
                        case "IEC (SP)":
                            performIECSPPageActions();

                            break;
                        case "IEC Spouse":
                            performIECSPPageActions();

                            break;

                        case "Upload Documents":
                            performFileUploadPageActions();

                            break;
                        case "Please provide your prospective worksite location:":
                            performUnitPageActions();

                            break;
                        case "Felony":
                            performFelonyPageActions();

                            break;
                        case "SSDI":
                            performSSDIPageActions();

                            break;
                        case "Philadelphia Military Service":
                            philadelphiaMServicePageActions();

                            break;

                        case "CA Military Service":
                            if (getScenarioName().contains("Call-In")) {
                                waitForLoad();
                                sleep(3000);
        //                        callinPage.callInCAMilitary();
                                break;
                            } else {
                                performCAMilitryPageActions();
                            }
                            break;

                        case "CA Earned Income Credit":
                            if (getScenarioName().contains("Call-In")) {
       //                         callinPage.callInCAEarnIncome();
                            } else {
                                performCAEarnedIncomePageActions();
                            }
                            break;
                        case "CA Felony":
                            if (getScenarioName().contains("Call-In")) {
       //                         callinPage.callInCAFelony();
                            } else {
                                performCAFelonyPageActions();
                            }
                            break;
                        case "CA General Assistance":
                            if (getScenarioName().contains("Call-In")) {
       //                         callinPage.callInCAGeneralAssistance();
                            } else {
                                performCAGeneralAssistancePageActions();
                            }
                            break;
                        case "CA Unemployed Full Time Student":
                            if (getScenarioName().contains("Call-In")) {
       //                         callinPage.callInCAUnemployedFullTimeStudent();
                            } else {
                                performCAUnempFullTimeStudentActions();
                            }
                            break;

                        case "Military Service SF-180":
                            if (getScenarioName().contains("Call-In")) {
       //                         saScreening.militoryServiceSF180();
                            } else {
                                performMilitaryServiceSF_180Actions();
                            }
                            break;

                        case "Forms Printing":
                            sleep(5000);
       //                     saScreening.sf150_Continue_Button().click();
                            waitForLoad();
                            break;


                        case "Hire Information":
                            if (getScenarioName().contains("WalmartWebIn")) {
         //                       webInWalmartpg.performHireSingleFlowWalmart();
                            } else {
                                performHireInfomationPageActions();
                            }

                            break;
                        case "Colorado WP":
                            performColoradoWPPageActions();
                            break;

                        case "UT Military Service":
                            performUTMilitaryPageActions();
                            break;

                        case "South Carolina FIP":
                            performSouthCarolinaPageActions();

                            break;

                        default:
                            String pageSource = getDriver().getPageSource();
                            if (pageSource.contains("Thank you") || pageSource.contains("Confirmation Number") || pageSource.contains("Screening Completed")) {
                                flag = true;

                                if (getScenarioName().contains("WalmartWebIn")) {
         //                           webInWalmartpg.walmart_DONE_BUTTON_WALMART().click();
                                    waitForLoad();
                                    String chkUrl = getDriver().getCurrentUrl();
                                    //if (!chkUrl.equalsIgnoreCase("https://wotc.adp.com/tcsstg/taxsitekiosk.html?walmartqa")) {

                                    //	verifyTest("Page is not redirecting : ", false);
                                    //}
                                }
                                if (getScenarioName().contains("Evo-Xml")) {
                                    flag = true;
                                    screeningPages.done_Btn_On_Screening_Compeletion_Page().click();
                                    sleep(2000);
                                    if (!screeningPages.google_Search_Btn().isDisplayed()) {
                                        verifyTest("Page is not redirecting to google page after cliking on Done btn in EVO XML screening last page", false);
                                    }
                                }if (isDisplayed(ScreeningLocators.confirmationPage_ConfirmationNumber_Text)) {
                                    flag = true;
                                    getConfirmationNumber();
                                    excelReaderWriter.writeSSNAndConfirmationNumber();

                                }
                            }else {
                                noMethodFlag = false;
                            }
                            break;
                    }
                    System.out.println("Screening complted for "+ pageToCall);
                    if(flag == true){
                        System.out.println("Screening is completed!!!  for SSN : " + getExcelData().get("SSN"));
                        break;
                    }
                    if(noMethodFlag == true){
                        verifyTest("Expected page method is not developed",false);
                        break;
                    }
                }
                verifyTest("User perform screening test");
            }catch(Exception e){
                System.out.println(e);
                verifyTest("Faliure in Screening",false);
            }
        }
    }


