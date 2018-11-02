package java.xeroAppTesting;
import java.Module_HomePage.HomePage;
import java.Module_Login.*;
import java.Module_SignUp.*;
public class RunTest extends ReusableFlows {



	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		initializeExtentReport();


		readORFile("./src/test/java/repository/config.properties");
		readConfigFile("./src/test/java/repository/config.properties");
		//		LoginPageTestScripts.TC01_validLogin();
		//		LoginPageTestScripts.TC02_incorrectPassword();
		//		LoginPageTestScripts.TC03_IncorrectUserName();	
		//		LoginPageTestScripts.TC04_ForgotPassword();
		//		SignUp.TC05_GetStarted(); - catpcha cant
		//		SignUp.TC07_verifyPrivacyPolicyLink();
		//		SignUp.TC08_newOffersLink();
		//		SignUp.TC09_accountantBookkeeperLink();
		//		HomePage.TC10_HomePageElements();
		//		HomePage.TC11_Logout();
		//		HomePage.TC12_UploadDP();
		//		HomePage.TC13_AddOrganisation();
		//		MyXero.TC14_PaidVersion();
		//	MyXero.TC18_QuickBooksVersion();
		extent.flush();


	}

}
