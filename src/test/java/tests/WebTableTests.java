package tests;
import driver.WebdriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class WebTableTests {

    public static void main(String[] args) {
        ChromeDriver driver = createDriverAndGetPage();
        updateTable(driver); // aici am apelat a doua metoda, aia pentru dinamic table data
        getTableDetails(driver); //aici apelam metoda testWebTable


        driver.quit();
    }

    public static ChromeDriver createDriverAndGetPage(){

        ChromeDriver driver = WebdriverManager.createChromeDriver();
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");  //aici ii zic sa acceseze site-ul respectiv
        return driver;

    }

    public static void getTableDetails(ChromeDriver driver){

        WebElement tableCaption = driver.findElement(By.cssSelector("#tablehere table caption"));
        System.out.println("The caption is: " + "'" + tableCaption.getText() + "'");
        System.out.println("");

        WebElement table = driver.findElement(By.cssSelector("#tablehere table"));  // presupun ca trebuie sa il cautam iar ca sa afisam noua valoare (e tot tableId de mai sus
        System.out.println("The ID is: " + "'" + table.getAttribute("id") + "'"); // eu incercasem cu .getText inainte si nu a mers
        System.out.println("");

        List<WebElement> tableRows = driver.findElements(By.cssSelector("#tablehere table tr"));
        for (int i = 0; i < tableRows.size();i++){
            WebElement currentRow = tableRows.get(i);
            if(i==0){
                List<WebElement> firstRowColumss = currentRow.findElements(By.cssSelector("th"));   //Aici nu prea am inteles de ce am mai selectat odata randul "th"
                System.out.println("The first column header is: " + firstRowColumss.get(0).getText());  //aici am selectat coloana 0, adica prima
                System.out.println("The second column header is: " + firstRowColumss.get(1).getText()); //aici am selectat coloana 1, adica a doua
                System.out.println(""); //pe asta l-am pus eu ca sa arate frumos in consola (e un rand gol)
            } else {

                List<WebElement> currentColumns = currentRow.findElements(By.cssSelector("td"));
                System.out.println("text din randul" + (i+1)+ ", coloana 1:" + currentColumns.get(0).getText());
                System.out.println("text din randul" + (i+1)+ ", coloana 2:" + currentColumns.get(1).getText());
                System.out.println("");

            }
        }

    }


    public static void updateTable(ChromeDriver driver){

        WebElement summary = driver.findElement(By.cssSelector("summary")); //aici am creat acest element "summay" care poate fi gasit dupa cssSelector-ul "summary"
        summary.click(); //aici i-am zis ce sa faca cu acel summary pe care l-am creat.

        //update caption
        WebElement caption = driver.findElement(By.id("caption")); // aici am cautat elementul dupa ID. ID-urile sunt unice pe pagina
        caption.clear(); // aici am golit textul de acolo de pe site;
        caption.sendKeys("I wrote this text :)"); // asa introducem text;

        //update table ID

        WebElement tableId = driver.findElement(By.cssSelector("#tableid"));
        tableId.clear();
        tableId.sendKeys("MyCustomID");

        //update table content

        WebElement jsonTableContent = driver.findElement(By.cssSelector("textarea#jsondata")); // de pe text area NU merge comanda gettext() trebuie cu get.attribute ("value") . Acele slash-uri se numesc "string escaping" - e ceva normal
        jsonTableContent.clear();
        jsonTableContent.sendKeys("[{\"name\": \"Labush\",\"age\": 50}, { \"name\": \"Matilda\", \"age\": 32}, {\"name\": \"John\", \"age\": 22 },{ \"name\": \"Cric\",   \"age\": 76   },{  \"name\": \"Dracula\",   \"age\": 432}]");
        System.out.println(jsonTableContent.getAttribute("value"));


        //press refresh button
        WebElement refreshTableButton = driver.findElement(By.id("refreshtable")); //butonul ala mare
        refreshTableButton.click();

    }
}

