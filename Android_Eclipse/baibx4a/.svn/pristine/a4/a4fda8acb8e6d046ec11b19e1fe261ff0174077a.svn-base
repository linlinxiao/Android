package com.baoxiao.service.group;

import java.util.ArrayList;
import java.util.List;
import com.baoxiao.model.Contact;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

public class ContactService {
	
	
	private static final String[] PHONES_PROJECTION = new String[] {
		Phone.CONTACT_ID, Phone.DISPLAY_NAME,  Phone.NUMBER }; 
	
	public List<Contact> getPhoneContacts(ContentResolver resolver){
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI,PHONES_PROJECTION, null, null, null);
		List<Contact> contacts = null;
		if (phoneCursor != null) {  
			contacts = new ArrayList<Contact>();
	        while (phoneCursor.moveToNext()) {  
	            String number = phoneCursor.getString(2);  
	            // 当手机号码为空的或者为空字段 跳过当前循环    
	            if (TextUtils.isEmpty(number))    
	                continue;  
	            // 得到联系人名称                
	            String name = phoneCursor.getString(1);
	            String id = phoneCursor.getString(0);
	            Contact contact = new Contact();
	            contact.setId(Integer.parseInt(id));
	            contact.setName(name);
	            contact.setPhone(number);
	            contacts.add(contact);
	        } 	
	        phoneCursor.close(); 
	    }
		return contacts;
	}  
}
