# AutomationTasks
this a trainning course by mohamb mohie by automatest round 32
course notes 
=================
locators assessment
1. risk of failure
2. maintainability
3. performance

getText() vs getAttribute("innerText")
getText() -> visible text white in dom
getAttribute("innerText") -> get text value for a given attribute(search for DOM property if not found search for DOM Attribute)
some text has no text or attribute 

getDomAttribute() -> get text value for a given attribute even if the element is not visible >> in DOM We Used All of US 
getDomProperty() ->in properties tab in dev tools 
all dom attribute appear in properties tab but not all properties appear in attributes tab
dom attribute is static -> if any change in page while user is active will appear in dom property not in dom attribute
ex: <input type="text" value="test"> -> value is dom attribute
if user change value to "test123" -> in dom attribute still "test" but in dom property will be "test123"

# therefore: we use Dom Attribute to read default value or static unchanged and Dom Property to read dynamic value
# get attribute is not a WC3 srandard method and depricated so we use getProperty or getDomAttribute
=====================
# Golden Formula:
=====================
//tagName[@attribute='value']

Variations:
//tagName[text()='value']

//tagName[@attribute]
//tagName[@attribute1='value1'][@attribute2='value2']

//tagName[@attribute='value']/childElement
//tagName[@attribute='value']//grandChildElement

(//tagName[@attribute='value'])[index]

//tagName[@attribute='value']
//tagName[contains(@attribute,'subsetValue')]

//tagName[contains(.,'value')]

//*[@attribute='value']

xpath axes:
//tagName[@attribute='value']/parent::parentTagName
//tagName[@attribute='value']/ancestor::ancestorTagName
//tagName[@attribute='value']/following-sibling::followingSiblingTagName
//tagName[@attribute='value']/preceding-sibling::precedingSiblingTagName

Golden Rules:
- locators must always satisfy your business requirements
- follow the formula
- use unique attributes, and unique values (not generic ones)
- use a locator that will match ONLY ONE ELEMENT
- never use the index unless explicitly stated in your business requirements
# Test Workflow Management
  # Test Cases:
  test cases are independent
  use before/after method for setup/teardown
  starts with navigation
  user performs some actions (not many)
  ends with assertion
  has one checkpoint
  multiple test cases per java class, one class per business module (ex: loginTests, registrationTests)
  all tests can exist in the same package
  # Test Scenarios:
  you cannot create it in one test method (multiple reasons for failure)
  test methods are dependent on each other (dependsOnMethods)
  the order of execution is not important (never use priority)
  each test method represents a validatable business action, one action and one checkpoint
  they share a common setup and teardown (before/after class)
  one class per test scenario (ex: checkOrderIsCompletedViaCreditCardPayment, checkOrderIsCompletedViaCashPayment)
  one package per business scope (ex: sanity tests, regression tests, client tests)
  
# actions on element 
element.isDisplayed(); // style="display:none;"
element.isEnabled(); // disabled
element.isSelected(); // checked, selected

element.getText(); > if white in DOM attributes 
element.getDomAttribute(""); if found in dom with value attribute
element.getDomProperty("");  if not font in dom with value attribute
element.getAttribute(""); generic search in dom attribute if not search in dom property , used in mobile only 

# element.click(); ->
1. لو الكليك هيوديك صفحه تانيه او يخلي element جديده تظهر  المفروض تتخلص من الreferences  لل element ده عشان ميديش stale element exceptions 
2. لو عملت click ده مش بي wait  يعني لوعملت لوجن ب user, pass then click  انت الي محتاج تتاكد ان حصل redirection
3. يعني متخزنش في web element عشان كده احنا بنخزن في by object
4. عشان تعمل click لازم يكون ال element يبقي visible,is dipayed=true و الhight,width اكبر من zero 
5. throw many exceptions
6. https://chatgpt.com/s/t_68ecba49d32481918353e87375267f63
7. solution > we store element locator (By object ) not element reference (webElment) 
8. and every time we use driver.findElement(locator) to re find element freshly 

element.sendKeys("Selenium", " ", "WebDriver");

# element.clear();
1. لو الelement مش موجود في فورم يبقي معرفكش 
2. لو ال element موجود جوه form هت reset ال value بتاعته للoriginal value في ال dom property يعني مش بتمسح
3. https://chatgpt.com/s/t_68ecbc788a708191a5d6d344193d9a59

# element.sendKeys();
1. ال input بتاعها اجه اسمها character sequence و ده parent for strings and other classes
2. في ال three dot بتاعه ال otptional يعني ممكن تبعت او متبعتش
3. ممكن تبعت كذا string مفصولين ب coma 
4. ممكن تكتب strings و بعدها ت send key enter or tab 
5. انت مش بتسخدمها عشان تset value ل input  او عشان ت type في elemnet و لكن هو بيsimulate عمليه الكتابه و may set its value 
6. settings values depend on dev design lang عشان كده كاتبين may يعني ممكن و كممكن 
ex: element.sendKeys("username", Keys.TAB, "password", Keys.TAB, Keys.ENTER, Keys.TAB, Keys.ENTER);

# implicit waits
1. syntax : driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
2. handle only one exception type : NoSuchElementException
3. ولو لقي الelement بيفضل مستني ال 10 ثوانيس كاملين 
4. مش بيهندل ال lazy loading

# explicit waits
1. syntax : WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(""));
2. فيها مشكله انك لازم تبقي عارف اسامي الexceptions  
3. لو دخلت جوهvisibilityOfElementLocated هتلاقيها بترجع elementIsVisible و لو جاله noSuchElement و staleElementReference مش بيهندله
4. لكن في الdocs  بتهندل ال not Found Exception 
5. element is visible >> بتستخدم isDisplayed() جواها 
6. بيدور كل نص ثانيه 