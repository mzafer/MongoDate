# MongoDate

Project to test the conversion issue and storing of date in MongoDB

After cloning  the project, run the test using

	C:\MongoDate>sbt
	> test
	
1. Using java.util.Date

The code by default uses Date and a custom context ( refer util.salat_context.scala). On running the test we do not get error but the below ouput

	JsonStr from a new testItem object :{"_id":"505663b65a2bcc63dd1d9e29","name":"Test Item","date":1347838902143}
	Json Str after creating a testItem object from a jsonStr :{"_id":"505663b65a2bcc63dd1d9e2a","name":"Test Item","date":-898433270}
	[info] TestSpec
	[info]
	[info] Test Item should
	[info] + be converted to Json
	[info] + be converted from Json
	[info]
	[info]

Note the date value in the second string, though there is not error, that is the issue.


2. Using joda.DateTime

Do the following updates to use DateTime instead of Date.

Update TestItem.scala, to use joda.DateTime 

	date:Option[DateTime] = None
	//date:Option[Date] = None

Update TestSpec.scala also to ues joda.DateTime

    	//var testItem = TestItem(name="Test Item",date=Some(new Date()))
	var testItem = TestItem(name="Test Item",date=Some(DateTime.now))

Now on running the tests we'll get the below error.

	[error] ! be converted to Json
	[error]     MatchError: -781612865 (of class java.lang.Integer) (Injectors.scala
	:61)

If we run the same test but with the default global context, Update the TestItem.scala to use the global context

	import com.novus.salat.global._
	//import util.salat_context._

We'll see the below error for the first test case

	[error] ! be converted to Json
	[error]     MatchError: 2012-09-16T23:37:01Z (of class java.lang.String) (Inject
	ors.scala:61)


Note that I have not used JodaDateTimeConvertors from casbah, using that does not change the above results. Also if you check your local database you'll notice that date is stored as int32 or String type based on the chosen DateStrategy

Added Test cases for JSON Enum.

To test run

	>test-only -- include testEnum 
