package com.example;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
*/
public class ServiceNowDemo {

	private WebDriver driver;
	@BeforeClass
	public void setUpChromeDriver() {
		//ChromeDriverManager.getInstance().setup();
		//FirefoxDriverManager.getInstance().setup();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neslavath\\Desktop\\Cucumber\\Cucumber\\chromedriver_win32\\chromedriver.exe");
		//driver =new FirefoxDriver();
		driver = new ChromeDriver();

	}
	
	@BeforeMethod
	public void setUp() {
		//driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.focus();");
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}

	public String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public String getCurrentWindowTitle(WebDriver driver) {
		String windowTitle = driver.getTitle();
		return windowTitle;
	}
	
//	@Test
	public void chromeBrowserTest1() throws Exception {	
		// Initializing webdriver wait object
		WebDriverWait wait = new WebDriverWait(driver, 30l);
		Hashtable<String, String> frames = new Hashtable<String, String>();
		frames.put("main", "#gsft_main");
		frames.put("nav", "#gsft_nav");
		ArrayList<String> gsft_nav_elements = new ArrayList<String>();
		gsft_nav_elements.add("filter");
		gsft_nav_elements.add("Create New");
		
		ArrayList<String> gsft_main_elements = new ArrayList<String>();
		gsft_main_elements.add("incident.number");
		gsft_main_elements.add("[id='sys_display.incident.caller_id']");
		gsft_main_elements.add("[id='lookup.incident.caller_id']");
		gsft_main_elements.add("input[id='incident.short_description']");
		gsft_main_elements.add("form[id='incident.do']");
		
		driver.get("https://dev23996.service-now.com/");
		waitForSeconds(5L);
		WebElement frame = driver.findElement(By.cssSelector("#gsft_main"));
		driver.switchTo().frame(frame);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Google@123");
		driver.findElement(By.cssSelector("#sysverb_login")).click();
		waitForSeconds(3L);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_nav")));
		System.out.println("====> "+driver.findElements(By.cssSelector("#gsft_nav")).size());
		if(gsft_nav_elements.contains("filter")) {
			driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_nav")));
			driver.findElement(By.id("filter")).sendKeys("Incident");
			waitForSeconds(2L);
			driver.switchTo().defaultContent();
		}
		
		if(gsft_nav_elements.contains("Create New")) {
			driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_nav")));
			driver.findElement(By.linkText("Create New")).click();
			waitForSeconds(2L);
			driver.switchTo().defaultContent();
		}
		
		if(gsft_main_elements.contains("incident.number")) {
			driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
			driver.findElement(By.id("incident.number")).clear();
			driver.findElement(By.id("incident.number")).sendKeys("Incident0001");
			waitForSeconds(2L);
			driver.switchTo().defaultContent();
		}
		
		String windowTitle= driver.getTitle();
		String mainWindow = driver.getWindowHandle();
		
		if(gsft_main_elements.contains("[id='sys_display.incident.caller_id']")) {
			driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
			driver.findElement(By.cssSelector("[id='sys_display.incident.caller_id']")).clear();
			driver.findElement(By.cssSelector("[id='sys_display.incident.caller_id']")).sendKeys("Abel Tuter");
			waitForSeconds(2L);
			driver.switchTo().defaultContent();
		}
		
		if(gsft_main_elements.contains("[id='lookup.incident.caller_id']")) {
			driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
			driver.findElement(By.cssSelector("[id='lookup.incident.caller_id']")).click();
			waitForSeconds(2L);
			driver.switchTo().defaultContent();
		}
		
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(windowTitle)) {
				driver.switchTo().window(currentWindowHandle);
			}
		}
		
		
		driver.findElement(By.cssSelector("#sys_user th[name='search']>button.icon-search")).click();
		waitForSeconds(3L);
		driver.findElement(By.cssSelector("#sys_user input[aria-label='Search name']")).sendKeys("Abel Tuter");
		
		driver.findElement(By.cssSelector("#sys_user th[name='search']>button.icon-search")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#sys_user_list")));
		waitForSeconds(3L);
		driver.findElement(By.xpath("//div[@id='sys_user_list']/*//td/a[text()='Abel Tuter']")).click();
		waitForSeconds(2L);
		driver.switchTo().window(mainWindow);
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
		
		driver.findElement(By.cssSelector("div[id='element.incident.short_description'] input[id='incident.short_description']")).clear();
		driver.findElement(By.cssSelector("div[id='element.incident.short_description'] input[id='incident.short_description']")).sendKeys("Description for Incident0001");
		waitForSeconds(3L);
		driver.findElement(By.cssSelector(".navbar_ui_actions #sysverb_insert")).click();
		
		//driver.findElement(By.cssSelector("#user_info_dropdown")).click();
		waitForSeconds(2L);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		waitForSeconds(3L);
		
	}
	
//	@Test
	public void updateIncidentTest() throws Exception {	
		// Initializing webdriver wait object
		WebDriverWait wait = new WebDriverWait(driver, 30l);
		driver.get("https://dev23996.service-now.com/");
		//waitForSeconds(5L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_main")));
		WebElement frame = driver.findElement(By.cssSelector("#gsft_main"));
		driver.switchTo().frame(frame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user_name")));
		driver.findElement(By.cssSelector("#user_name")).sendKeys("admin");
		driver.findElement(By.cssSelector("#user_password")).sendKeys("Google@123");
		driver.findElement(By.cssSelector("#sysverb_login")).click();
		waitForSeconds(3L);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_nav")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_logout>button")));
		System.out.println("====> "+driver.findElements(By.cssSelector("#gsft_nav")).size());
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_nav")));
		waitForSeconds(2L);
		driver.findElement(By.id("filter")).sendKeys("Incident");
		waitForSeconds(2L);
		
		driver.findElement(By.cssSelector(".submenu li[modulename='All']>button+a[target='gsft_main']")).click();
		driver.switchTo().defaultContent();
		
		//Switch to gsft_main frame
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
		
		driver.findElement(By.cssSelector("#list_nav_incident input[placeholder='Search']")).sendKeys("Incident0001");
		driver.findElement(By.cssSelector("#list_nav_incident input[placeholder='Search']")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table[id='incident_table']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Incident0001']")));
		
		driver.findElement(By.xpath("//a[text()='Incident0001']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".navbar[data-type='section_head']")));
		waitForSeconds(2L);
		
		driver.findElement(By.cssSelector("div[id='element.incident.short_description'] input[id='incident.short_description']")).clear();
		driver.findElement(By.cssSelector("div[id='element.incident.short_description'] input[id='incident.short_description']")).sendKeys("Updated Description for Incident0001");
		
		driver.findElement(By.cssSelector(".navbar_ui_actions #sysverb_update")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table[id='incident_table']")));
		driver.switchTo().defaultContent();
		//click Logout
		//driver.findElement(By.cssSelector("#gsft_logout>button")).click();
		
	}
	
//	@Test
	public void contextMenuTest() throws Exception {	
		// Initializing webdriver wait object
		WebDriverWait wait = new WebDriverWait(driver, 30l);
		driver.get("https://dev23996.service-now.com/");
		//waitForSeconds(5L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_main")));
		WebElement frame = driver.findElement(By.cssSelector("#gsft_main"));
		driver.switchTo().frame(frame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user_name")));
		driver.findElement(By.cssSelector("#user_name")).sendKeys("admin");
		driver.findElement(By.cssSelector("#user_password")).sendKeys("Google@123");
		driver.findElement(By.cssSelector("#sysverb_login")).click();
		waitForSeconds(3L);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_nav")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_logout>button")));
		System.out.println("====> "+driver.findElements(By.cssSelector("#gsft_nav")).size());
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_nav")));
		waitForSeconds(2L);
		driver.findElement(By.id("filter")).sendKeys("Incident");
		waitForSeconds(2L);
		
		driver.findElement(By.cssSelector(".submenu li[modulename='All']>button+a[target='gsft_main']")).click();
		driver.switchTo().defaultContent();
		waitForSeconds(2L);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
		waitForSeconds(2L);
		// Clicking element to get context MENU
		driver.findElement(By.cssSelector("button[data-type='list2_top_title']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#context_list_titleincident")));
		
		List<WebElement>contextMenuList = driver.findElements(By.cssSelector("#context_list_titleincident>div[class='context_item']"));
		contextMenuList.forEach(menuElement -> {
			System.out.println("Menu Item ===>"+ menuElement.getText());
		});
		// Select View in context Menu
		moveToElementByTextFromMenu(driver, contextMenuList, "View");
		waitForSeconds(2L);
		List<WebElement>contextMenus = driver.findElements(By.xpath("//div[@class='context_menu' and contains(@style, 'block')]"));
		System.out.println(contextMenus.size());
		WebElement element1 = null;
		for(WebElement menuElement: contextMenus) {
			System.out.println("Menu Item ===>"+ menuElement.getAttribute("id"));
			if(!menuElement.getAttribute("id").trim().equals("context_list_titleincident")) {
				element1 = menuElement;
			}
		};
		
		List<WebElement>contextMenu2 = element1.findElements(By.cssSelector("div[class='context_item']"));
		contextMenu2.forEach(menuElement -> {
			System.out.println("Menu Item2 ===>"+ menuElement.getText());
		});
		
		moveToElementByTextFromMenu(driver, contextMenu2, "Major incidents");
	}
	
	public void selectElementByTextFromMenu(WebDriver driver, List<WebElement> contextMenuList, String elementText) {
		contextMenuList.forEach(menuElement -> {
			System.out.println("Menu Item ===>"+ menuElement.getText());
			boolean elementFound = false;
			if(menuElement.getText().trim().startsWith(elementText) && (!elementFound)) {
				Actions ref = new Actions(driver);
				ref.moveToElement(menuElement);
				ref.click().build().perform();
				elementFound = true;
			}
		});
	}
	
	public void moveToElementByTextFromMenu(WebDriver driver, List<WebElement> contextMenuList, String elementText) {
		contextMenuList.forEach(menuElement -> {
			System.out.println("Menu Item ===>"+ menuElement.getText());
			boolean elementFound = false;
			if(menuElement.getText().trim().startsWith(elementText) && (!elementFound)) {
				Actions ref = new Actions(driver);
				ref.moveToElement(menuElement);
				ref.build().perform();
				elementFound = true;
			}
		});
	}
	
	@Test
	public void createChangeTest() throws Exception {	
		// Initializing webdriver wait object
		WebDriverWait wait = new WebDriverWait(driver, 30l);
		driver.get("https://dev23996.service-now.com/");
		//waitForSeconds(5L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_main")));
		WebElement frame = driver.findElement(By.cssSelector("#gsft_main"));
		driver.switchTo().frame(frame);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#user_name")));
		driver.findElement(By.cssSelector("#user_name")).sendKeys("admin");
		driver.findElement(By.cssSelector("#user_password")).sendKeys("Google@123");
		driver.findElement(By.cssSelector("#sysverb_login")).click();
		waitForSeconds(3L);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_nav")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gsft_logout>button")));
		System.out.println("====> "+driver.findElements(By.cssSelector("#gsft_nav")).size());
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_nav")));
		waitForSeconds(2L);
		driver.findElement(By.id("filter")).sendKeys("Change");
		waitForSeconds(2L);
		
		//driver.findElement(By.linkText("Create New")).click();  /// 
		waitForSeconds(2L);
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#gsft_main")));
		waitForSeconds(2L);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Change Request")));
		// Clicking element to create Normal: Changes
		driver.findElement(By.linkText("Normal: Changes without predefined plans that require approval and CAB authorization.")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change_request.number")));
		driver.findElement(By.id("sys_display.change_request.cmdb_ci")).sendKeys("*ANNIE-IBM");
		waitForSeconds(2L);
		driver.findElement(By.id("change_request.short_description")).sendKeys("Test case short description");
		driver.findElement(By.id("change_request.description")).sendKeys("Automated Description");
		clickTabWithText(driver, "Notes");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change_request.work_notes")));
		driver.findElement(By.id("change_request.work_notes")).sendKeys("Hello first work note entry.");
		
		driver.findElement(By.cssSelector("button.btn-icon.icon-menu.navbar-btn")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#context_1")));
		
		List<WebElement>contextMenuList = driver.findElements(By.cssSelector("#context_1>div[class='context_item']"));
		contextMenuList.forEach(menuElement -> {
			System.out.println("Menu Item ===>"+ menuElement.getText());
		});
		// Select View in context Menu
		selectElementByTextFromMenu(driver, contextMenuList, "Save");
		waitForSeconds(2L);
		
		
	}
	
	private void clickTabWithText(WebDriver driver, String string) {
		String tabXpath = "//span[@class='tab_caption_text' and text()='"+string+"']";
		driver.findElement(By.xpath(tabXpath)).click();
	}

	public void clickWithJavaScript(WebElement element) throws Exception {
		try {
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Clicking on element with using java script click");

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			} else {
				System.out.println("Unable to click on element");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
	public void waitForSeconds(long seconds) {
		long stoptime = System.currentTimeMillis() + (seconds * 1000);
		while (System.currentTimeMillis() < stoptime) {
		}
	}
}
