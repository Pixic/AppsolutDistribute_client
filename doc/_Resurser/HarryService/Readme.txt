Denna fil har i princip allt som den behöver i sig, Man skickar ett start o stop kommando.
Övrig kod som jag behövde implementera las in på mainActivity, i mitt fall på två stycken
extra menyknappar som las in på nedanstående..

Det viktiga egentligen för att få det o starta är ///startService(new Intent(this, ServiceTagAccount.class));///
och således är ///stopService(new Intent(this, ServiceTagAccount.class));/// till för att stoppa

Lägg in den där ni vill ha den

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