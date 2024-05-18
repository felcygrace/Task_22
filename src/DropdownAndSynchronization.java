import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropdownAndSynchronization {

	public static void main(String[] args) {
		//set system property for the firefox browser
		 System.setProperty("webdriver.gecko.driver", "C:\\Users\\FELCY\\Desktop\\geckodriver-v0.34.0-win32\\geckodriver.exe");
		// Initialize the Firefox driver 
	        WebDriver driver = new FirefoxDriver();
		
		//Navigating the URL
		driver.navigate().to("https://phptravels.com/demo/");
		
		//Using pageLoadTimeout 
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		
		//Using ImplicitlyWait for page to wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		//maximize window
		driver.manage().window().maximize();
		
		//fill the form 
		driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Felcy");
		driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Grace");
		driver.findElement(By.xpath("//input[@name='business_name']")).sendKeys("Grace_bikes");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("felcygrace001@gmail.com");
		
		//calculate the numbers
		String num1 = driver.findElement(By.xpath("//span[@id='numb1']")).getText();
		String num2 = driver.findElement(By.xpath("//span[@id='numb2']")).getText();
		
		int num3 = Integer.parseInt(num1)+Integer.parseInt(num2);
		driver.findElement(By.xpath("//input[@id='number']")).sendKeys(String.valueOf(num3));
		
		//Clicking Submit button
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		//Using WebDriverWait
				WebElement thanksMessage = driver.findElement(By.xpath("//strong[text()=' Thank you!']"));
				WebElement successMessage = driver.findElement(By.xpath("//p[@class='text-center cw']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				wait.until(ExpectedConditions.visibilityOf(thanksMessage));
				wait.until(ExpectedConditions.visibilityOf(successMessage));

				String thanks = driver.findElement(By.xpath("//strong[text()=' Thank you!']")).getText();
				String success = driver.findElement(By.xpath("//p[@class='text-center cw']")).getText();

				
				//Validating the page
				String verify = driver.findElement(By.xpath(" //h2[contains(text(),'Demo Request Form')]")).getText(); 
				if(verify.contains("Demo"))
				{                                  
					System.out.println("Form submitted ");
					System.out.println(verify);
				} 
				else 
				{
					System.out.println("Form not submitted");
				}
										
				//Taking Screenshot
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File source = screenshot.getScreenshotAs(OutputType.FILE);
				File destination = new File("C:\\Users\\FELCY\\eclipse-workspace\\Task_22\\output.png");   
				
				try {
					FileUtils.copyFile(source, destination);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//Closing the driver
				driver.close();  
		
		

	}

}
