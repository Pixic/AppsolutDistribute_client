Denna fil har i princip allt som den beh�ver i sig, Man skickar ett start o stop kommando.
�vrig kod som jag beh�vde implementera las in p� mainActivity, i mitt fall p� tv� stycken
extra menyknappar som las in p� nedanst�ende..

Det viktiga egentligen f�r att f� det o starta �r ///startService(new Intent(this, ServiceTagAccount.class));///
och s�ledes �r ///stopService(new Intent(this, ServiceTagAccount.class));/// till f�r att stoppa

L�gg in den d�r ni vill ha den

			////Kod i MainActivity////

    public boolean onOptionsItemSelected(MenuItem item) { 
    	
    	switch (item.getItemId()) {
    	case R.id.itemServiceStart:
    		startService(new Intent(this, ServiceTagAccount.class));
    		break;
    	case R.id.itemServiceStop:
    		stopService(new Intent(this, ServiceTagAccount.class));
    		break;
    	}
    	return menuMenu(item);