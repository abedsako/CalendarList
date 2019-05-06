# CalendarList
this calendar based on recyclerview


Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

-------------------------------------------------------------------

Add the calendar dependency in app's build.gradle


	dependencies {
	        implementation 'com.github.abedsako:CalendarList:1.0.0'
	}

--------------------------------------------------------------------

How to use

in onCreate :

        DatesList datesList = new DatesList(this);

	// you can implement onclickListener
	// DatesList datesList = new DatesList(this,this);

        recyclerView.setAdapter(datesList.getDatesAdapter(recyclerView));


you can change colors and more :

        Options options = Options.getInstance();
	options.setMultiSelect(true);
	options.setYearTextColor(Color.parseColor("#000000"));



