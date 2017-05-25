package com.ffhp.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ffhp.MainPageActivity;
import com.ffhp.adapter.TestAdapter;
import com.ffhp.model.CartProduct;
import com.ffhp.model.Customer;
import com.ffhp.model.EmailGet;
import com.ffhp.model.ImageUrl;
import com.ffhp.model.OrderClass;
import com.ffhp.model.ProductInfo;
import com.ffhp.model.ShippingClass;
import com.ffhp.model.UpdateInfo;
import com.ffhp.resource.SessionManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 3;
	private static String DB_PATH = "";
	private static Context mContext;
	private SQLiteDatabase mDataBase;
    // Database Name
    private static final String DATABASE_NAME = "ffhpuser";
    // Users table name
    private static final String TABLE_FFHPUSER = "users";
    // Cart Table name
    private static final String TABLE_FFHPCART = "cart";
    // Product Info Table name
    private static final String TABLE_FFHPPRODUCT = "productinfo";
    // WishList Table name
    private static final String TABLE_FFHPWISHLIST = "wishlist";
    // Imageurl Table name
    private static final String TABLE_FFHPIMAGEURL = "imageurl";
    // Update Table name
    private static final String TABLE_FFHPUPDATE = "updatecount";
    // YoYo Table name
    private static final String TABLE_FFHPYOYO = "yoyoproducts";
    // Order Table name
    private static final String TABLE_FFHPORDER = "orderdetails";
    // Contact Table name
    private static final String TABLE_FFHPCONTACT = "ffhpcontacts";
	// Shippig method Table name
	//private static final String TABLE_FFHPSHIPPING = "shippingmethod";
    
    // Order Table Columns names
    private static final String KEY_PHONE_NUM = "phone_num";
    // Order Table Columns names
    private static final String KEY_ORDER_ID = "order_id";
    // Users Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL_ID = "email_id";
    private static final String KEY_PASSWORD = "password";
	private static final String KEY_NAME = "name";
	//Cart Table columns names
	private static final String KEY_QTY = "qty";
	private static final String KEY_PRICE_TOT = "price_total";
	private static final String KEY_ITEMS = "items";
	
	// Product Update Table
	private static final String KEY_DATE = "update_date";
	private static final String KEY_COUNT = "count";
	
	//Product Info Table columns
	private static final String KEY_PRODUCT_ID = "product_id";
	private static final String KEY_SKU = "sku";
	private static final String KEY_SET = "set_id";
	private static final String KEY_TYPE = "type";
	private static final String KEY_CATEGORIES = "categories";
	private static final String KEY_WEBSITES = "websites";
    private static final String KEY_TYPE_ID = "type_id";
    private static final String KEY_PRODUCT = "name";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_SHORT_DESCRIPTION = "short_description";
	private static final String KEY_WEIGHT = "weight";
	private static final String KEY_OLD_ID = "old_id";
	private static final String KEY_FROM_DATE = "news_from_date";
	private static final String KEY_TO_DATE = "news_to_date";
	private static final String KEY_STATUS = "status";
	private static final String KEY_URL_KEY = "url_key";
	private static final String KEY_URL_PATH = "url_path";
	private static final String KEY_MAX_COUNT = "max_checked_count";
	private static final String KEY_MIN_COUNT = "min_checked_count";
	private static final String KEY_VISIBLE = "visibility";
	private static final String KEY_CATEGORY_ID = "category_ids";
    private static final String KEY_REQ_OPT = "required_options";
	private static final String KEY_HAS_OPT = "has_options";
	private static final String KEY_IMG_LABEL = "image_label";
	private static final String KEY_S_IMG_LABEL = "small_image_label";
	private static final String KEY_THUMB_LABEL = "thumbnail_label";
	private static final String KEY_CREATED_AT = "created_at";
	private static final String KEY_UPDATED_AT = "updated_at";
	private static final String KEY_COUNTRY_OF_MANU = "country_of_manufacture";
	private static final String KEY_PRICE = "price";
	private static final String KEY_GROUP_PRICE = "group_price";
	private static final String KEY_SPL_PRICE = "special_price";
	private static final String KEY_SPL_FROM_DATE = "special_from_date";
	private static final String KEY_SPL_TO_DATE = "special_to_date";
	private static final String KEY_TIER_PRICE = "tier_price";
    private static final String KEY_MINIMAL_PRICE = "minimal_price";
	private static final String KEY_MSRP_ENB = "msrp_enabled";
	private static final String KEY_MSRP_DISP = "msrp_display_actual_price_type";
	private static final String KEY_MSRP = "msrp";
	private static final String KEY_ENB_GOOGLE = "enable_googlecheckout";
	private static final String KEY_TAX_ID = "tax_class_id";
	private static final String KEY_META_TITLE = "meta_title";
	private static final String KEY_META_KEY = "meta_keyword";
	private static final String KEY_META_DES = "meta_description";
	private static final String KEY_IS_REC = "is_recurring";
	private static final String KEY_REC_PROF = "recurring_profile";
	private static final String KEY_CUST_DSGN = "custom_design";
	private static final String KEY_CUST_DSGN_FROM = "custom_design_from";
	private static final String KEY_CUST_DSGN_TO = "custom_design_to";
	private static final String KEY_CUS_LAY = "custom_layout_update";
	private static final String KEY_PAGE_LAY = "page_layout";
	private static final String KEY_OPT_CONT = "options_container";
	private static final String KEY_GIFT_MSG_AVA = "gift_message_available";
	private static final String KEY_SKU_P_GIFT = "sku_of_product_gift";
	private static final String KEY_P_GIFT_ENB = "is_product_gift_enabled";
	private static final String KEY_STOCK = "stock";
	private static final String KEY_SMALL_IMAGE = "small_image";

	/*//Shipping Method Table Columns
	private static final String KEY_PK = "pk";
	private static final String KEY_WEBSITE_ID = "website_id";
	private static final String KEY_COUNTRY = "dest_country_id";
	private static final String KEY_REGION_ID = "dest_region_id";
	private static final String KEY_CITY_ID = "dest_city";
	private static final String KEY_AIPCODE = "dest_aip";
	private static final String KEY_ZIPCODE = "dest_zip_to";
	private static final String KEY_CONDITION_NAME = "condition_name";
	private static final String KEY_ORDER_SUBTOTAL_FROM = "condition_from_value";
	private static final String KEY_ORDER_SUBTOTAL_TO = "condition_to_value";
	private static final String KEY_SHIPPING_PRICE = "price";
	private static final String KEY_DELIVERY_COST = "cost";
	private static final String KEY_DELIVERY_TYPE = "delivery_type";*/

	//Imageurl Table Columns names
    private static final String KEY_FILE = "file";
	private static final String KEY_LABEL = "label";
	private static final String KEY_POSITION = "position";
	private static final String KEY_EXCLUDE = "exclude";
	private static final String KEY_URL = "url";
	
	//yoyo Table Columns names
	private static final String KEY_REF_ID = "refId";
	SessionManager session;
	
	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		if(android.os.Build.VERSION.SDK_INT >= 17){
		       DB_PATH = context.getApplicationInfo().dataDir + "/databases/";         
		    }
		    else
		    {
		       DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		    }
		    this.mContext = context;
		    session=new SessionManager(mContext);
	}
	public void createDataBase() throws IOException
	{
	    //If database not exists copy it from the assets

	    boolean mDataBaseExist = checkDataBase();
	    if(!mDataBaseExist)
	    {
	        this.getReadableDatabase();
	        this.close();
	        try 
	        {
	            //Copy the database from assests
	            copyDataBase();
	            Log.e("Error", "createDatabase database created");
	        } 
	        catch (IOException mIOException) 
	        {
	            throw new Error("ErrorCopyingDataBase");
	        }
	    }
	}
	    //Check that the database exists here: /data/data/your package/databases/Da Name
	    private boolean checkDataBase()
	    {
	        File dbFile = new File(DB_PATH + DATABASE_NAME);
	        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
	        return dbFile.exists();
	    }

	    //Copy the database from assets
	    private void copyDataBase() throws IOException
	    {
	        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
	        String outFileName = DB_PATH + DATABASE_NAME;
	        OutputStream mOutput = new FileOutputStream(outFileName);
	        byte[] mBuffer = new byte[1024];
	        int mLength;
	        while ((mLength = mInput.read(mBuffer))>0)
	        {
	            mOutput.write(mBuffer, 0, mLength);
	        }
	        mOutput.flush();
	        mOutput.close();
	        mInput.close();
	    }

	    //Open the database, so we can query it
	    public boolean openDataBase() throws SQLException
	    {
	        String mPath = DB_PATH + DATABASE_NAME;
	        //Log.v("mPath", mPath);
	        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
	        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
	        return mDataBase != null;
	    }

	    @Override
	    public synchronized void close() 
	    {
	        if(mDataBase != null)
	            mDataBase.close();
	        super.close();
	    }
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		/*String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_FFHPORDER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL_ID + " TEXT," + KEY_ORDER_ID + " TEXT NOT NULL UNIQUE,"
                + KEY_NAME + " TEXT" + ")";*/
		String CREATE_FFHP_CONTACT = "CREATE TABLE " + TABLE_FFHPCONTACT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PHONE_NUM + " TEXT,"
				+ KEY_EMAIL_ID + " TEXT" + ")";
		/*String CREATE_YOYO_TABLE = "CREATE TABLE " + TABLE_FFHPYOYO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REF_ID + " TEXT,"
				+ KEY_PRODUCT_ID + " TEXT NOT NULL UNIQUE" + ")";*/
		
		String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_FFHPIMAGEURL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FILE + " TEXT," + KEY_LABEL + " TEXT," + KEY_POSITION + " TEXT,"
                + KEY_EXCLUDE + " TEXT," + KEY_URL + " TEXT," + KEY_PRODUCT_ID + " TEXT" + ")";
		
		String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_FFHPUSER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_EMAIL_ID + " TEXT NOT NULL UNIQUE," + KEY_PASSWORD + " TEXT,"
                + KEY_NAME + " TEXT" + ")";
		
		String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_FFHPCART + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_ID + " TEXT NOT NULL UNIQUE," + KEY_PRODUCT + " TEXT,"
                + KEY_PRICE + " TEXT," + KEY_QTY + " TEXT," + KEY_PRICE_TOT + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_ITEMS + " TEXT" + ")";
		
		String CREATE_PRODUCT_INFO_TABLE = "CREATE TABLE " + TABLE_FFHPPRODUCT + "(" 
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_ID + " TEXT NOT NULL UNIQUE," + KEY_SKU + " TEXT," + KEY_SET + " TEXT,"
				+ KEY_TYPE + " TEXT," + KEY_TYPE_ID + " TEXT," + KEY_PRODUCT + " TEXT," + KEY_DESCRIPTION + " TEXT,"
				+ KEY_SHORT_DESCRIPTION + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_OLD_ID + " TEXT," + KEY_FROM_DATE + " TEXT,"
				+ KEY_TO_DATE + " TEXT," + KEY_STATUS + " TEXT," + KEY_URL_KEY + " TEXT," + KEY_URL_PATH + " TEXT,"
				+ KEY_MAX_COUNT + " TEXT," + KEY_MIN_COUNT + " TEXT," + KEY_VISIBLE + " TEXT," + KEY_REQ_OPT + " TEXT,"
				+ KEY_HAS_OPT + " TEXT," + KEY_IMG_LABEL + " TEXT," + KEY_S_IMG_LABEL + " TEXT," + KEY_THUMB_LABEL + " TEXT,"
				+ KEY_CREATED_AT + " TEXT," + KEY_UPDATED_AT + " TEXT," + KEY_COUNTRY_OF_MANU + " TEXT," + KEY_PRICE + " TEXT,"
				+ KEY_SPL_PRICE + " TEXT," + KEY_SPL_FROM_DATE + " TEXT," + KEY_SPL_TO_DATE + " TEXT," + KEY_MINIMAL_PRICE + " TEXT,"
				+ KEY_MSRP_ENB + " TEXT," + KEY_MSRP_DISP + " TEXT," + KEY_MSRP + " TEXT," + KEY_ENB_GOOGLE + " TEXT,"
				+ KEY_TAX_ID + " TEXT," + KEY_META_TITLE + " TEXT," + KEY_META_KEY + " TEXT," + KEY_META_DES + " TEXT,"
				+ KEY_IS_REC + " TEXT," + KEY_REC_PROF + " TEXT," + KEY_CUST_DSGN + " TEXT," + KEY_CUST_DSGN_FROM + " TEXT,"
				+ KEY_CUST_DSGN_TO + " TEXT," + KEY_CUS_LAY + " TEXT," + KEY_PAGE_LAY + " TEXT," + KEY_OPT_CONT + " TEXT,"
				+ KEY_GIFT_MSG_AVA + " TEXT," + KEY_SKU_P_GIFT + " TEXT," + KEY_P_GIFT_ENB + " TEXT," + KEY_STOCK + " TEXT," + KEY_SMALL_IMAGE + " TEXT" + ")";
		
		String CREATE_WISH_LIST_TABLE = "CREATE TABLE " + TABLE_FFHPWISHLIST + "(" 
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_PRODUCT_ID + " TEXT NOT NULL UNIQUE" + ")";
		
		String CREATE_UPDATE_TABLE = "CREATE TABLE " + TABLE_FFHPUPDATE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT," + KEY_PRODUCT_ID + " TEXT,"
                + KEY_COUNT + " TEXT" + ")";

		/*String CREATE_SHIPPING_TABLE = "CREATE TABLE " + TABLE_FFHPSHIPPING + "("
				+ KEY_ID + " INTEGER," + KEY_PK + " TEXT PRIMARY_KEY," + KEY_WEBSITE_ID + " TEXT,"
				+ KEY_COUNTRY + " TEXT," + KEY_REGION_ID + " TEXT," + KEY_CITY_ID + " TEXT," + KEY_AIPCODE + " TEXT,"
				+ KEY_ZIPCODE + " TEXT," + KEY_CONDITION_NAME + " TEXT," + KEY_ORDER_SUBTOTAL_FROM + " TEXT,"
				+ KEY_ORDER_SUBTOTAL_TO + " TEXT," + KEY_SHIPPING_PRICE + " TEXT," + KEY_DELIVERY_COST + " TEXT,"
				+ KEY_DELIVERY_TYPE + " TEXT" + ")";*/

		db.execSQL(CREATE_IMAGE_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CART_TABLE);
        db.execSQL(CREATE_PRODUCT_INFO_TABLE);
        db.execSQL(CREATE_WISH_LIST_TABLE);
        db.execSQL(CREATE_UPDATE_TABLE);
        db.execSQL(CREATE_FFHP_CONTACT);
		//db.execSQL(CREATE_SHIPPING_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPORDER);
		switch (oldVersion) {
	    case 1: 
	        db.execSQL("ALTER TABLE " + 
	            TABLE_FFHPPRODUCT + " ADD COLUMN " + KEY_STOCK + " TEXT");
			db.execSQL("ALTER TABLE " + TABLE_FFHPPRODUCT + " ADD COLUMN " + KEY_SMALL_IMAGE + " TEXT");
			/*String CREATE_SHIPPING_TABLE1 = "CREATE TABLE " + TABLE_FFHPSHIPPING + "("
					+ KEY_ID + " INTEGER," + KEY_PK + " TEXT PRIMARY_KEY," + KEY_WEBSITE_ID + " TEXT,"
					+ KEY_COUNTRY + " TEXT," + KEY_REGION_ID + " TEXT," + KEY_CITY_ID + " TEXT," + KEY_AIPCODE + " TEXT,"
					+ KEY_ZIPCODE + " TEXT," + KEY_CONDITION_NAME + " TEXT," + KEY_ORDER_SUBTOTAL_FROM + " TEXT,"
					+ KEY_ORDER_SUBTOTAL_TO + " TEXT," + KEY_SHIPPING_PRICE + " TEXT," + KEY_DELIVERY_COST + " TEXT,"
					+ KEY_DELIVERY_TYPE + " TEXT" + ")";

			db.execSQL(CREATE_SHIPPING_TABLE1);*/
			break;
		case 2:
			db.execSQL("ALTER TABLE " + TABLE_FFHPPRODUCT + " ADD COLUMN " + KEY_SMALL_IMAGE + " TEXT");
			/*String CREATE_SHIPPING_TABLE2 = "CREATE TABLE " + TABLE_FFHPSHIPPING + "("
					+ KEY_ID + " INTEGER," + KEY_PK + " TEXT PRIMARY_KEY," + KEY_WEBSITE_ID + " TEXT,"
					+ KEY_COUNTRY + " TEXT," + KEY_REGION_ID + " TEXT," + KEY_CITY_ID + " TEXT," + KEY_AIPCODE + " TEXT,"
					+ KEY_ZIPCODE + " TEXT," + KEY_CONDITION_NAME + " TEXT," + KEY_ORDER_SUBTOTAL_FROM + " TEXT,"
					+ KEY_ORDER_SUBTOTAL_TO + " TEXT," + KEY_SHIPPING_PRICE + " TEXT," + KEY_DELIVERY_COST + " TEXT,"
					+ KEY_DELIVERY_TYPE + " TEXT" + ")";

			db.execSQL(CREATE_SHIPPING_TABLE2);
			break;
		case 3:
			String CREATE_SHIPPING_TABLE = "CREATE TABLE " + TABLE_FFHPSHIPPING + "("
					+ KEY_ID + " INTEGER," + KEY_PK + " TEXT PRIMARY_KEY," + KEY_WEBSITE_ID + " TEXT,"
					+ KEY_COUNTRY + " TEXT," + KEY_REGION_ID + " TEXT," + KEY_CITY_ID + " TEXT," + KEY_AIPCODE + " TEXT,"
					+ KEY_ZIPCODE + " TEXT," + KEY_CONDITION_NAME + " TEXT," + KEY_ORDER_SUBTOTAL_FROM + " TEXT,"
					+ KEY_ORDER_SUBTOTAL_TO + " TEXT," + KEY_SHIPPING_PRICE + " TEXT," + KEY_DELIVERY_COST + " TEXT,"
					+ KEY_DELIVERY_TYPE + " TEXT" + ")";

			db.execSQL(CREATE_SHIPPING_TABLE);*/

	    }
		/*db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPUSER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPCART);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPPRODUCT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPIMAGEURL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPWISHLIST);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPUPDATE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FFHPCONTACT);*/
		
	    //onCreate(db);
	}
	
	//Add a new userinfo
  	public void createUsersInfo(EmailGet userinfo)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_NAME, userinfo.getName());
  		content.put(KEY_PHONE_NUM, userinfo.getMobile_no());
  		content.put(KEY_EMAIL_ID, userinfo.getEmailid());
  		db.insert(TABLE_FFHPCONTACT, null, content);
  		//db.close();
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	//get single userinfo
  	public EmailGet getUsersInfo(int id)
  	{
  		SQLiteDatabase db=this.getReadableDatabase();
  		Cursor cursor=db.query(TABLE_FFHPCONTACT, new String[] { KEY_ID,
  				KEY_NAME, KEY_PHONE_NUM, KEY_EMAIL_ID }, KEY_ID + "=?",
              new String[] { String.valueOf(id) }, null, null, null, null);
  		
  		if(cursor !=null)
  			cursor.moveToFirst();
  		
  		EmailGet updateinfo=new EmailGet(Integer.parseInt(cursor.getString(0)),
  	            cursor.getString(1), cursor.getString(2),false,cursor.getString(3));

  		return updateinfo;
  	}
  	//get list of updateinfo
  	public List<EmailGet> getAllUsersInfo()
  	{
  		List<EmailGet> list=new ArrayList<EmailGet>();
  		try{
  		String selectQuery="SELECT * FROM " + TABLE_FFHPCONTACT;
  		
  		SQLiteDatabase db=this.getWritableDatabase();
  		Cursor cursor=db.rawQuery(selectQuery, null);
  		
  		if(cursor.moveToFirst())
  		{
  			do{
  				EmailGet updateinfo=new EmailGet();
  				updateinfo.setId(Integer.parseInt(cursor.getString(0)));
  				updateinfo.setName(cursor.getString(1));
  				updateinfo.setMobile_no(cursor.getString(2));
  				updateinfo.setSelected(false);
  				updateinfo.setEmailid(cursor.getString(3));
  				
  				list.add(updateinfo);
  			}while(cursor.moveToNext());
  		}
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  		return list;
  	}
  	//update UpdateInfo
  	public int updateUsersInfo(EmailGet updateinfo) {
          SQLiteDatabase db = this.getWritableDatabase();
          
          ContentValues values = new ContentValues();
          values.put(KEY_NAME, updateinfo.getName());
          values.put(KEY_PHONE_NUM, updateinfo.getMobile_no());
          values.put(KEY_EMAIL_ID, updateinfo.getEmailid());
          
          return db.update(TABLE_FFHPCONTACT, values, KEY_ID + " = ?",
                  new String[] { String.valueOf(updateinfo.getId()) });
      }
      // Deleting single UpdateInfo
      public void deleteUsersInfo(EmailGet updateinfo) {
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(TABLE_FFHPCONTACT, KEY_ID + " = ?",
                  new String[] { String.valueOf(updateinfo.getId()) });
          db.close();
      }
      // Getting UpdateInfo Count
      public int getUsersInfoCount() {
          String countQuery = "SELECT  * FROM " + TABLE_FFHPCONTACT;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
          //cursor.close();
          // return count
          return cursor.getCount();
      }
	
	//Add a new Updateinfo
  	public void createUpdate(UpdateInfo updateinfo)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_DATE, updateinfo.getUpdate_date());
  		content.put(KEY_PRODUCT_ID, updateinfo.getProduct_id());
  		content.put(KEY_COUNT, updateinfo.getCount());
  		
  		db.insert(TABLE_FFHPUPDATE, null, content);
  		db.close();
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	//get single UpdateInfo
  	public UpdateInfo getUpdateInfo(int id)
  	{
  		SQLiteDatabase db=this.getReadableDatabase();
  		
  		Cursor cursor=db.query(TABLE_FFHPUPDATE, new String[] { KEY_ID,
  				KEY_DATE, KEY_PRODUCT_ID, KEY_COUNT }, KEY_ID + "=?",
              new String[] { String.valueOf(id) }, null, null, null, null);
  		
  		if(cursor !=null)
  			cursor.moveToFirst();
  		
  		UpdateInfo updateinfo=new UpdateInfo(Integer.parseInt(cursor.getString(0)),
  	            cursor.getString(1), cursor.getString(2), cursor.getString(3));
  		
  		return updateinfo;
  	}
  	//get list of updateinfo
  	public List<UpdateInfo> getAllUpdateInfo()
  	{
  		List<UpdateInfo> list=new ArrayList<UpdateInfo>();
  		try{
  		String selectQuery="SELECT * FROM " + TABLE_FFHPUPDATE;
  		
  		SQLiteDatabase db=this.getWritableDatabase();
  		Cursor cursor=db.rawQuery(selectQuery, null);
  		
  		if(cursor.moveToFirst())
  		{
  			do{
  				UpdateInfo updateinfo=new UpdateInfo();
  				updateinfo.setId(Integer.parseInt(cursor.getString(0)));
  				updateinfo.setUpdate_date(cursor.getString(1));
  				updateinfo.setProduct_id(cursor.getString(2));
  				updateinfo.setCount(cursor.getString(3));
  				
  				list.add(updateinfo);
  			}while(cursor.moveToNext());
  		}
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  		return list;
  	}
  	//update UpdateInfo
  	public int updateUpdateInfo(UpdateInfo updateinfo) {
          SQLiteDatabase db = this.getWritableDatabase();
          
          ContentValues values = new ContentValues();
          values.put(KEY_DATE, updateinfo.getUpdate_date());
          values.put(KEY_PRODUCT_ID, updateinfo.getProduct_id());
          values.put(KEY_COUNT, updateinfo.getCount());
          
          return db.update(TABLE_FFHPUPDATE, values, KEY_ID + " = ?",
                  new String[] { String.valueOf(updateinfo.getId()) });
      }
      // Deleting single UpdateInfo
      public void deleteUpdateInfo(UpdateInfo updateinfo) {
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(TABLE_FFHPUPDATE, KEY_ID + " = ?",
                  new String[] { String.valueOf(updateinfo.getId()) });
          db.close();
      }
      // Getting UpdateInfo Count
      public int getUpdateInfoCount() {
          String countQuery = "SELECT  * FROM " + TABLE_FFHPUPDATE;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
          //cursor.close();
          // return count
          return cursor.getCount();
      }
	
	//Add a new Imageurl
  	public void createImageurl(ImageUrl imageurl)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_FILE, imageurl.getFile());
  		content.put(KEY_LABEL, imageurl.getLabel());
  		content.put(KEY_POSITION, imageurl.getPosition());
  		content.put(KEY_EXCLUDE, imageurl.getExclude());
  		content.put(KEY_URL, imageurl.getUrl());
  		content.put(KEY_PRODUCT_ID, imageurl.getProduct_id());
  		db.insert(TABLE_FFHPIMAGEURL, null, content);
  		db.close();
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	public void createImageurl1(ImageUrl imageurl)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_FILE, imageurl.getFile());
  		content.put(KEY_LABEL, imageurl.getLabel());
  		content.put(KEY_POSITION, imageurl.getPosition());
  		content.put(KEY_EXCLUDE, imageurl.getExclude());
  		content.put(KEY_URL, imageurl.getUrl());
  		content.put(KEY_PRODUCT_ID, imageurl.getProduct_id());
  		db.insert(TABLE_FFHPIMAGEURL, null, content);
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	//get single Imageurl
  	public ImageUrl getImageurl(int id)
  	{
  		SQLiteDatabase db=this.getReadableDatabase();
  		
  		Cursor cursor=db.query(TABLE_FFHPIMAGEURL, new String[] { KEY_ID,
  				KEY_FILE, KEY_LABEL, KEY_POSITION,KEY_EXCLUDE,KEY_URL,KEY_PRODUCT_ID }, KEY_ID + "=?",
              new String[] { String.valueOf(id) }, null, null, null, null);
  		
  		if(cursor !=null)
  			cursor.moveToFirst();
  		
  		ImageUrl imageurl=new ImageUrl(Integer.parseInt(cursor.getString(0)),
  	            cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5),cursor.getString(6));
  		
  		return imageurl;
  	}
  //get single Imageurl
  	public String getImageurl(String id)
  	{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT url FROM imageurl WHERE product_id = ?", new String[] {id});
  		
        if(cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return "";
  	}
  	//get list of Imageurl
  	public List<ImageUrl> getAllImageurl()
  	{
  		List<ImageUrl> list=new ArrayList<ImageUrl>();
  		
  		String selectQuery="SELECT * FROM " + TABLE_FFHPIMAGEURL;
  		
  		SQLiteDatabase db=this.getWritableDatabase();
  		Cursor cursor=db.rawQuery(selectQuery, null);
  		
  		if(cursor.moveToFirst())
  		{
  			do{
  				ImageUrl imageurl=new ImageUrl();
  				imageurl.setId(Integer.parseInt(cursor.getString(0)));
  				imageurl.setFile(cursor.getString(1));
  				imageurl.setLabel(cursor.getString(2));
  				imageurl.setPosition(cursor.getString(3));
  				imageurl.setExclude(cursor.getString(4));
  				imageurl.setUrl(cursor.getString(5));
  				imageurl.setProduct_id(cursor.getString(6));
  				list.add(imageurl);
  			}while(cursor.moveToNext());
  		}
  		return list;
  	}
  	//update Imageurl
  	public int updateImageurl(ImageUrl imageurl) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          values.put(KEY_FILE, imageurl.getFile());
          values.put(KEY_LABEL, imageurl.getLabel());
          values.put(KEY_POSITION, imageurl.getPosition());
          values.put(KEY_EXCLUDE, imageurl.getExclude());
          values.put(KEY_URL, imageurl.getUrl());
          values.put(KEY_PRODUCT_ID, imageurl.getProduct_id());
          // updating row
          return db.update(TABLE_FFHPIMAGEURL, values, KEY_ID + " = ?",
                  new String[] { String.valueOf(imageurl.getId()) });
      }
	// Deleting All ImageUrl 
    public void deleteAllImageUrl() {
  	  SQLiteDatabase db = this.getWritableDatabase();
  	  try{
  	    db.delete(TABLE_FFHPIMAGEURL, null, null);
  	    db.close();
  	  }
  	  catch (Exception e) {
			// TODO: handle exception
  		db.close();
  		  e.printStackTrace();
		}
    }
      // Deleting single Imageurl
      public void deleteImageurl(ImageUrl imageurl) {
    	  try{
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(TABLE_FFHPIMAGEURL, KEY_ID + " = ?",
                  new String[] { String.valueOf(imageurl.getId()) });
          db.close();
    	  }
  		catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
      }
      // Getting Imageurl Count
      public int getImageurlCount() {
          String countQuery = "SELECT  * FROM " + TABLE_FFHPIMAGEURL;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
          //cursor.close();
          // return count
          return cursor.getCount();
      }
  //Add a new Product
  	public void createProduct(CartProduct product)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_PRODUCT_ID, product.getProduct_id());
  		content.put(KEY_PRODUCT, product.getName());
  		content.put(KEY_PRICE, product.getPrice());
  		content.put(KEY_QTY, product.getQty());
  		content.put(KEY_PRICE_TOT, product.getPrice_tot());
  		content.put(KEY_WEIGHT, product.getWeight());
  		content.put(KEY_ITEMS, product.getItem());
  		db.insert(TABLE_FFHPCART, null, content);
  		db.close();
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	//get single product
  	public CartProduct getProduct(int id)
  	{
  		SQLiteDatabase db=this.getReadableDatabase();
  		
  		Cursor cursor=db.query(TABLE_FFHPCART, new String[] { KEY_ID,
  				KEY_PRODUCT_ID, KEY_PRODUCT, KEY_PRICE,KEY_QTY,KEY_PRICE_TOT,KEY_WEIGHT,KEY_ITEMS }, KEY_ID + "=?",
              new String[] { String.valueOf(id) }, null, null, null, null);
  		
  		if(cursor !=null)
  			cursor.moveToFirst();
  		
  		CartProduct product=new CartProduct(Integer.parseInt(cursor.getString(0)),
  	            cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),cursor.getString(7));
  		
  		return product;
  	}
  	//get list of product
  	public List<CartProduct> getAllProduct()
  	{
  		List<CartProduct> list=new ArrayList<CartProduct>();
  		
  		String selectQuery="SELECT * FROM " + TABLE_FFHPCART;
  		
  		SQLiteDatabase db=this.getWritableDatabase();
  		Cursor cursor=db.rawQuery(selectQuery, null);
  		
  		if(cursor.moveToFirst())
  		{
  			do{
  				CartProduct product=new CartProduct();
  				product.setId(Integer.parseInt(cursor.getString(0)));
  				product.setProduct_id(cursor.getString(1));
  				product.setName(cursor.getString(2));
  				product.setPrice(cursor.getString(3));
  				product.setQty(cursor.getString(4));
  				product.setPrice_tot(cursor.getString(5));
  				product.setWeight(cursor.getString(6));
  				product.setItem(cursor.getString(7));
  				list.add(product);
  			}while(cursor.moveToNext());
  		}
  		return list;
  	}
  	//update product
  	public int updateProduct(CartProduct product) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          values.put(KEY_PRODUCT_ID, product.getProduct_id());
          values.put(KEY_PRODUCT, product.getName());
          values.put(KEY_PRICE, product.getPrice());
          values.put(KEY_QTY, product.getQty());
          values.put(KEY_PRICE_TOT, product.getPrice_tot());
          values.put(KEY_WEIGHT, product.getWeight());
          values.put(KEY_ITEMS, product.getItem());
          // updating row
          return db.update(TABLE_FFHPCART, values, KEY_ID + " = ?",
                  new String[] { String.valueOf(product.getId()) });
      }
  	// Deleting All product 
    public void deleteAllProduct() {
  	  SQLiteDatabase db = this.getWritableDatabase();
  	  try{
  	    db.delete(TABLE_FFHPCART, null, null);
  	    db.close();
  	  }
  	  catch (Exception e) {
			// TODO: handle exception
  		  e.printStackTrace();
  		  db.close();
		}
    }
      // Deleting single product
      public void deleteProduct(CartProduct product) {
    	  try{
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(TABLE_FFHPCART, KEY_PRODUCT_ID + " = ?",
                  new String[] { String.valueOf(product.getProduct_id()) });
          db.close();
    	  }
  		catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
      }
      // Getting Product Count
      public int getProductCount() {
          String countQuery = "SELECT  * FROM " + TABLE_FFHPCART;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
          //cursor.close();
          // return count
          return cursor.getCount();
      }
    //create a new customer
  	public void createUser(Customer customer)
  	{
  		try{
  		SQLiteDatabase db=this.getWritableDatabase();
  		
  		ContentValues content=new ContentValues();
  		content.put(KEY_EMAIL_ID, customer.getEmail_id());
  		content.put(KEY_PASSWORD, customer.getPassword());
  		content.put(KEY_NAME, customer.getName());
  		
  		db.insert(TABLE_FFHPUSER, null, content);
  		db.close();
  		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	}
  	//get single customer
  	public Customer getCustomer(int id)
  	{
  		SQLiteDatabase db=this.getReadableDatabase();
  		
  		Cursor cursor=db.query(TABLE_FFHPUSER, new String[] { KEY_ID,
              KEY_EMAIL_ID, KEY_PASSWORD, KEY_NAME }, KEY_ID + "=?",
              new String[] { String.valueOf(id) }, null, null, null, null);
  		
  		if(cursor !=null)
  			cursor.moveToFirst();
  		
  		Customer customer=new Customer(Integer.parseInt(cursor.getString(0)),
  	            cursor.getString(1), cursor.getString(2),cursor.getString(3));
  		
  		return customer;
  	}
	//get single customer
	public Customer getCustomer(String email)
	{
		SQLiteDatabase db=this.getReadableDatabase();

		Cursor cursor=db.query(TABLE_FFHPUSER, new String[] { KEY_ID,
						KEY_EMAIL_ID, KEY_PASSWORD, KEY_NAME }, KEY_EMAIL_ID + "=?",
				new String[] { String.valueOf(email) }, null, null, null, null);

		if(cursor !=null)
			cursor.moveToFirst();

		Customer customer=new Customer(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2),cursor.getString(3));

		return customer;
	}
  	//get list of customer
  	public List<Customer> getAllCustomer()
  	{
  		List<Customer> list=new ArrayList<Customer>();
  		
  		String selectQuery="SELECT * FROM " + TABLE_FFHPUSER;
  		
  		SQLiteDatabase db=this.getWritableDatabase();
  		Cursor cursor=db.rawQuery(selectQuery, null);
  		
  		if(cursor.moveToFirst())
  		{
  			do{
  				Customer customer=new Customer();
  				customer.setId(Integer.parseInt(cursor.getString(0)));
  				customer.setEmail_id(cursor.getString(1));
  				customer.setPassword(cursor.getString(2));
  				customer.setName(cursor.getString(3));
  				list.add(customer);
  			}while(cursor.moveToNext());
  		}
  		return list;
  	}
  	//update user
  	public int updateCustomer(Customer customer) {
          SQLiteDatabase db = this.getWritableDatabase();
   
          ContentValues values = new ContentValues();
          values.put(KEY_EMAIL_ID, customer.getEmail_id());
          values.put(KEY_PASSWORD, customer.getPassword());
          values.put(KEY_NAME, customer.getName());
          // updating row
          return db.update(TABLE_FFHPUSER, values, KEY_ID + " = ?",
                  new String[] { String.valueOf(customer.getId()) });
      }
      // Deleting single user
      public void deleteContact(Customer customer) {
    	  try{
          SQLiteDatabase db = this.getWritableDatabase();
          db.delete(TABLE_FFHPUSER, KEY_ID + " = ?",
                  new String[] { String.valueOf(customer.getId()) });
          db.close();
    	  }
  		catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
      }
      // Getting user Count
      public int getContactsCount() {
          String countQuery = "SELECT  * FROM " + TABLE_FFHPUSER;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(countQuery, null);
          //cursor.close();
          // return count
          return cursor.getCount();
      }
      
    //create a new Order
    	public void createOrder(OrderClass order)
    	{
    		try{
    		SQLiteDatabase db=this.getWritableDatabase();
    		
    		ContentValues content=new ContentValues();
    		content.put(KEY_EMAIL_ID, order.getEmailid());
    		content.put(KEY_ORDER_ID, order.getOrder_id());
    		content.put(KEY_NAME, order.getName());
    		
    		db.insert(TABLE_FFHPORDER, null, content);
    		db.close();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
    	}
    	//get single Order
    	public OrderClass getOrder(int id)
    	{
    		SQLiteDatabase db=this.getReadableDatabase();
    		
    		Cursor cursor=db.query(TABLE_FFHPORDER, new String[] { KEY_ID,
                KEY_EMAIL_ID, KEY_ORDER_ID, KEY_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
    		
    		if(cursor !=null)
    			cursor.moveToFirst();
    		
    		OrderClass order=new OrderClass(Integer.parseInt(cursor.getString(0)),
    	            cursor.getString(1), cursor.getString(2),cursor.getString(3));
    		
    		return order;
    	}
    	//get list of Order
    	public List<OrderClass> getAllOrder()
    	{
    		List<OrderClass> list=new ArrayList<OrderClass>();
    		
    		String selectQuery="SELECT * FROM " + TABLE_FFHPORDER;
    		
    		SQLiteDatabase db=this.getWritableDatabase();
    		Cursor cursor=db.rawQuery(selectQuery, null);
    		
    		if(cursor.moveToFirst())
    		{
    			do{
    				OrderClass order=new OrderClass();
    				order.setId(Integer.parseInt(cursor.getString(0)));
    				order.setEmailid(cursor.getString(1));
    				order.setOrder_id(cursor.getString(2));
    				order.setName(cursor.getString(3));
    				list.add(order);
    			}while(cursor.moveToNext());
    		}
    		return list;
    	}
    	//update order
    	public int updateOrder(OrderClass orderClass) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL_ID, orderClass.getEmailid());
            values.put(KEY_ORDER_ID, orderClass.getOrder_id());
            values.put(KEY_NAME, orderClass.getName());
            // updating row
            return db.update(TABLE_FFHPORDER, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(orderClass.getId()) });
        }
        // Deleting single order
        public void deleteContact(OrderClass order) {
        	try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_FFHPORDER, KEY_ID + " = ?",
                    new String[] { String.valueOf(order.getId()) });
            db.close();
        	}
    		catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
        }
        // Getting Order Count
        public int getOrderCount() {
            String countQuery = "SELECT  * FROM " + TABLE_FFHPORDER;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            //cursor.close();
            // return count
            return cursor.getCount();
        }
      
    //Add a new ProductInfo
    	public void createProductInfo(ProductInfo product)
    	{
    		try{
    		SQLiteDatabase db=this.getWritableDatabase();
    		
    		ContentValues content=new ContentValues();
    		content.put(KEY_PRODUCT_ID,product.getProduct_id());
    		content.put(KEY_SKU,product.getSku());
    		content.put(KEY_SET,product.getSet());
    		content.put(KEY_TYPE,product.getType());
    		//content.put(KEY_CATEGORIES,product.getCategories());
    		//content.put(KEY_WEBSITES,product.getWebsites());
    		content.put(KEY_TYPE_ID,product.getType_id());
    		content.put(KEY_PRODUCT,product.getName());
    		content.put(KEY_DESCRIPTION,product.getDescription());
    		content.put(KEY_SHORT_DESCRIPTION,product.getShort_description());
    		content.put(KEY_WEIGHT,product.getWeight());
    		content.put(KEY_OLD_ID,product.getOld_id());
    		content.put(KEY_FROM_DATE,product.getNews_from_date());
    		content.put(KEY_TO_DATE,product.getNews_to_date());
    		content.put(KEY_STATUS,product.getStatus());
    		content.put(KEY_URL_KEY,product.getUrl_key());
    		content.put(KEY_URL_PATH,product.getUrl_path());
    		content.put(KEY_MAX_COUNT,product.getMax_checked_count());
    		content.put(KEY_MIN_COUNT,product.getMin_checked_count());
    		content.put(KEY_VISIBLE,product.getVisibility());
    		//content.put(KEY_CATEGORY_ID,product.getCategory_ids());
    		content.put(KEY_REQ_OPT,product.getRequired_options());
    		content.put(KEY_HAS_OPT,product.getHas_options());
    		content.put(KEY_IMG_LABEL,product.getImage_label());
    		content.put(KEY_S_IMG_LABEL,product.getSmall_image_label());
    		content.put(KEY_THUMB_LABEL,product.getThumbnail_label());
    		content.put(KEY_CREATED_AT,product.getCreated_at());
    		content.put(KEY_UPDATED_AT,product.getUpdated_at());
    		content.put(KEY_COUNTRY_OF_MANU,product.getCountry_of_manufacture());
    		content.put(KEY_PRICE,product.getPrice());
    		//content.put(KEY_GROUP_PRICE,product.getGroup_price());
    		content.put(KEY_SPL_PRICE,product.getSpecial_price());
    		content.put(KEY_SPL_FROM_DATE,product.getSpecial_from_date());
    		content.put(KEY_SPL_TO_DATE,product.getSpecial_to_date());
    		//content.put(KEY_TIER_PRICE,product.getTier_price());
    		content.put(KEY_MINIMAL_PRICE,product.getMinimal_price());
    		content.put(KEY_MSRP_ENB,product.getMsrp_enabled());
    		content.put(KEY_MSRP_DISP,product.getMsrp_display_actual_price_type());
    		content.put(KEY_MSRP,product.getMsrp());
    		content.put(KEY_ENB_GOOGLE,product.getEnable_googlecheckout());
    		content.put(KEY_TAX_ID,product.getTax_class_id());
    		content.put(KEY_META_TITLE,product.getMeta_title());
    		content.put(KEY_META_KEY,product.getMeta_keyword());
    		content.put(KEY_META_DES,product.getMeta_description());
    		content.put(KEY_IS_REC,product.getIs_recurring());
    		content.put(KEY_REC_PROF,product.getRecurring_profile());
    		content.put(KEY_CUST_DSGN,product.getCustom_design());
    		content.put(KEY_CUST_DSGN_FROM,product.getCustom_design_from());
    		content.put(KEY_CUST_DSGN_TO,product.getCustom_design_to());
    		content.put(KEY_CUS_LAY,product.getCustom_layout_update());
    		content.put(KEY_PAGE_LAY,product.getPage_layout());
    		content.put(KEY_OPT_CONT,product.getOptions_container());
    		content.put(KEY_GIFT_MSG_AVA,product.getGift_message_available());
    		content.put(KEY_SKU_P_GIFT,product.getSku_of_product_gift());
    		content.put(KEY_P_GIFT_ENB,product.getIs_product_gift_enabled());
    		content.put(KEY_STOCK,product.getStock());
			content.put(KEY_SMALL_IMAGE,product.getSmall_image());

    		db.insert(TABLE_FFHPPRODUCT, null, content);
    		db.close();
    		}
    		catch (Exception e) {
    			// TODO: handle exception
    			//e.printStackTrace();
    		}
    	}
    	//get single productInfo
    	public ProductInfo getProductInfo(int id)
    	{
    		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_FFHPPRODUCT, new String[] { KEY_ID,
				KEY_PRODUCT_ID, KEY_SKU, KEY_SET, KEY_TYPE, KEY_PRODUCT,
				KEY_DESCRIPTION, KEY_SHORT_DESCRIPTION, KEY_WEIGHT,
				KEY_FROM_DATE, KEY_TO_DATE, KEY_STATUS, KEY_URL_KEY,
				KEY_MAX_COUNT, KEY_MIN_COUNT, KEY_CREATED_AT, KEY_UPDATED_AT,
				KEY_PRICE, KEY_SPL_PRICE, KEY_SPL_FROM_DATE, KEY_SPL_TO_DATE,
				KEY_STOCK, KEY_SMALL_IMAGE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
    		if(cursor !=null)
    			cursor.moveToFirst();
    		
    		ProductInfo product=new ProductInfo(Integer.parseInt(cursor.getString(0)),
    	            cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(6), cursor.getString(7), 
    	            cursor.getString(8), cursor.getString(9), cursor.getString(11), cursor.getString(12), cursor.getString(13), cursor.getString(14), cursor.getString(16), 
    	            cursor.getString(17), cursor.getString(24), cursor.getString(25), cursor.getString(27),cursor.getString(28),cursor.getString(29),cursor.getString(30),cursor.getString(51),cursor.getString(52));
    		return product;
    	}
    	//get list of productInfo
    	public List<ProductInfo> getAllProductInfoBystatus()
    	{
    		List<ProductInfo> list=new ArrayList<ProductInfo>();
    		String selectQuery="SELECT * FROM " + TABLE_FFHPPRODUCT + " WHERE "+ KEY_STATUS + " =?";
    		SQLiteDatabase db=this.getWritableDatabase();
    		Cursor cursor=db.rawQuery(selectQuery, new String[]{"1"});
    		if(cursor.moveToFirst())
    		{
    			do{
    				ProductInfo product=new ProductInfo();
    				product.setId(Integer.parseInt(cursor.getString(0)));
    				product.setProduct_id(cursor.getString(1));
    				product.setSku(cursor.getString(2));
    				product.setSet(cursor.getString(3));
    				product.setType(cursor.getString(4));
    				product.setName(cursor.getString(6));
    				product.setDescription(cursor.getString(7));
    				product.setShort_description(cursor.getString(8));
    				product.setWeight(cursor.getString(9));
    				product.setNews_from_date(cursor.getString(11));
    				product.setNews_to_date(cursor.getString(12));
    				product.setStatus(cursor.getString(13));
    				product.setUrl_key(cursor.getString(14));
    				product.setMax_checked_count(cursor.getString(16));
    				product.setMin_checked_count(cursor.getString(17));
    				product.setCreated_at(cursor.getString(24));
    				product.setUpdated_at(cursor.getString(25));
    				product.setPrice(cursor.getString(27));
    				product.setSpecial_price(cursor.getString(28));
    				product.setSpecial_from_date(cursor.getString(29));
    				product.setSpecial_to_date(cursor.getString(30));
    				product.setStock(cursor.getString(51));
					product.setSmall_image(cursor.getString(52));
    				list.add(product);
    			}while(cursor.moveToNext());
    		}
    		return list;
    	}
    	
    	//get list of productInfo
    	public List<ProductInfo> getAllProductInfo()
    	{
    		List<ProductInfo> list=new ArrayList<ProductInfo>();
    		String selectQuery="SELECT * FROM " + TABLE_FFHPPRODUCT;
    		SQLiteDatabase db=this.getWritableDatabase();
    		Cursor cursor=db.rawQuery(selectQuery, null);
    		if(cursor.moveToFirst())
    		{
    			do{
    				ProductInfo product=new ProductInfo();
    				product.setId(Integer.parseInt(cursor.getString(0)));
    				product.setProduct_id(cursor.getString(1));
    				product.setSku(cursor.getString(2));
    				product.setSet(cursor.getString(3));
    				product.setType(cursor.getString(4));
    				product.setName(cursor.getString(6));
    				product.setDescription(cursor.getString(7));
    				product.setShort_description(cursor.getString(8));
    				product.setWeight(cursor.getString(9));
    				product.setNews_from_date(cursor.getString(11));
    				product.setNews_to_date(cursor.getString(12));
    				product.setStatus(cursor.getString(13));
    				product.setUrl_key(cursor.getString(14));
    				product.setMax_checked_count(cursor.getString(16));
    				product.setMin_checked_count(cursor.getString(17));
    				product.setCreated_at(cursor.getString(24));
    				product.setUpdated_at(cursor.getString(25));
    				product.setPrice(cursor.getString(27));
    				product.setSpecial_price(cursor.getString(28));
    				product.setSpecial_from_date(cursor.getString(29));
    				product.setSpecial_to_date(cursor.getString(30));
    				product.setStock(cursor.getString(51));
					product.setSmall_image(cursor.getString(52));
    				list.add(product);
    			}while(cursor.moveToNext());
    		}
    		return list;
    	}
    	
    	//update productInfo
    	public int updateProductInfo(ProductInfo product) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues content = new ContentValues();
            content.put(KEY_PRODUCT_ID,product.getProduct_id());
            content.put(KEY_SKU,product.getSku());
    		content.put(KEY_PRODUCT,product.getName());
    		content.put(KEY_DESCRIPTION,product.getDescription());
    		content.put(KEY_SHORT_DESCRIPTION,product.getShort_description());
    		content.put(KEY_WEIGHT,product.getWeight());
    		content.put(KEY_FROM_DATE,product.getNews_from_date());
    		content.put(KEY_TO_DATE,product.getNews_to_date());
    		content.put(KEY_STATUS,product.getStatus());
    		content.put(KEY_MAX_COUNT,product.getMax_checked_count());
    		content.put(KEY_MIN_COUNT,product.getMin_checked_count());
    		content.put(KEY_CREATED_AT,product.getCreated_at());
    		content.put(KEY_UPDATED_AT,product.getUpdated_at());
    		content.put(KEY_PRICE,product.getPrice());
    		content.put(KEY_SPL_PRICE,product.getSpecial_price());
    		content.put(KEY_SPL_FROM_DATE,product.getSpecial_from_date());
    		content.put(KEY_SPL_TO_DATE,product.getSpecial_to_date());
    		content.put(KEY_STOCK,product.getStock());
			content.put(KEY_SMALL_IMAGE,product.getSmall_image());
            // updating row
            return db.update(TABLE_FFHPPRODUCT, content, KEY_PRODUCT_ID + " = ?",
                    new String[] { String.valueOf(product.getProduct_id()) });
        }
		//get single Imageurl
		public String getProductsImageurl(String id)
		{
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("SELECT small_image FROM productinfo WHERE product_id = ?", new String[] {id});

			if(cursor.moveToFirst()) {
				return cursor.getString(0);
			}
			return "";
		}
    	//update Single price productInfo
    	public int updateProductInfoPrice(ProductInfo product) {
            SQLiteDatabase db = this.getWritableDatabase();
     
            ContentValues content = new ContentValues();
            content.put(KEY_PRODUCT_ID,product.getProduct_id());
    		content.put(KEY_PRICE,product.getPrice());
            // updating row
            return db.update(TABLE_FFHPPRODUCT, content, KEY_PRODUCT_ID + " = ?",
                    new String[] { String.valueOf(product.getProduct_id()) });
        }
    	
    	// Deleting single productInfo
        public void deleteProductInfo(ProductInfo product) {
        	try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_FFHPPRODUCT, KEY_ID + " = ?",
                    new String[] { String.valueOf(product.getId()) });
            db.close();
        	}
    		catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
        }
        // Getting ProductInfo Count
        public int getProductCountInfo() {
            String countQuery = "SELECT  * FROM " + TABLE_FFHPPRODUCT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            //cursor.close();
            // return count
            return cursor.getCount();
        }
        
      //Add a new WishList product
      	public void createWishlist(CartProduct product)
      	{
      		try{
      		SQLiteDatabase db=this.getWritableDatabase();
      		
      		ContentValues content=new ContentValues();
      		content.put(KEY_PRODUCT_ID, product.getProduct_id());
      		db.insert(TABLE_FFHPWISHLIST, null, content);
      		db.close();
      		}
    		catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    		}
      	}
      //get single wish list product
      	public CartProduct getWishlistProduct(int id)
      	{
      		SQLiteDatabase db=this.getReadableDatabase();
      		
      		Cursor cursor=db.query(TABLE_FFHPWISHLIST, new String[] { KEY_ID, KEY_PRODUCT_ID }, KEY_ID + "=?",
                  new String[] { String.valueOf(id) }, null, null, null, null);
      		
      		if(cursor !=null)
      			cursor.moveToFirst();
      		
      		CartProduct product=new CartProduct(Integer.parseInt(cursor.getString(0)),cursor.getString(1));
      		
      		return product;
      	}
      	//get list of wish list
      	public List<CartProduct> getAllWishlist()
      	{
      		List<CartProduct> list=new ArrayList<CartProduct>();
      		
      		String selectQuery="SELECT * FROM " + TABLE_FFHPWISHLIST;
      		
      		SQLiteDatabase db=this.getWritableDatabase();
      		Cursor cursor=db.rawQuery(selectQuery, null);
      		
      		if(cursor.moveToFirst())
      		{
      			do{
      				CartProduct product=new CartProduct();
      				product.setId(Integer.parseInt(cursor.getString(0)));
      				product.setProduct_id(cursor.getString(1));
      				list.add(product);
      			}while(cursor.moveToNext());
      		}
      		return list;
      	}
          // Deleting single wishlist 
          public void deleteWishlist(CartProduct product) {
        	  try{
              SQLiteDatabase db = this.getWritableDatabase();
              db.delete(TABLE_FFHPWISHLIST, KEY_PRODUCT_ID + " = ?",
                      new String[] { String.valueOf(product.getProduct_id()) });
              db.close();
        	  }
      		catch (Exception e) {
      			// TODO: handle exception
      			e.printStackTrace();
      		}
          }
          // Deleting single wishlist 
          public void deleteAllWishlist() {
        	  SQLiteDatabase db = this.getWritableDatabase();
        	  try{
        	    db.delete(TABLE_FFHPWISHLIST, null, null);
        	    db.close();
        	  }
        	  catch (Exception e) {
				// TODO: handle exception
        		  e.printStackTrace();
        		  db.close();
			}
          }
          // Getting wishlist Count
          public int getWishlistCount() {
              String countQuery = "SELECT  * FROM " + TABLE_FFHPWISHLIST;
              SQLiteDatabase db = this.getReadableDatabase();
              Cursor cursor = db.rawQuery(countQuery, null);
              //cursor.close();
              // return count
              return cursor.getCount();
          }

	/*//Add a new Shipping
	public void createShippingInfo(ShippingClass shippinginfo)
	{
		try{
			SQLiteDatabase db=this.getWritableDatabase();

			ContentValues content=new ContentValues();
			content.put(KEY_PK, shippinginfo.getPk());
			content.put(KEY_WEBSITE_ID, shippinginfo.getWebsite_id());
			content.put(KEY_COUNTRY, shippinginfo.getDest_country_id());
			content.put(KEY_REGION_ID, shippinginfo.getDest_region_id());
			content.put(KEY_CITY_ID, shippinginfo.getDest_city());
			content.put(KEY_AIPCODE, shippinginfo.getDest_aip());
			content.put(KEY_ZIPCODE, shippinginfo.getDest_zip_to());
			content.put(KEY_CONDITION_NAME, shippinginfo.getCondition_name());
			content.put(KEY_ORDER_SUBTOTAL_FROM, shippinginfo.getCondition_from_value());
			content.put(KEY_ORDER_SUBTOTAL_TO, shippinginfo.getCondition_to_value());
			content.put(KEY_SHIPPING_PRICE, shippinginfo.getPrice());
			content.put(KEY_DELIVERY_COST, shippinginfo.getCost());
			content.put(KEY_DELIVERY_TYPE, shippinginfo.getDelivery_type());
			db.insert(TABLE_FFHPSHIPPING, null, content);
			//db.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//get single Shipping
	public ShippingClass getShippingInfo(int id)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_FFHPSHIPPING, new String[] { KEY_ID, KEY_PK, KEY_WEBSITE_ID,
				KEY_COUNTRY, KEY_REGION_ID, KEY_CITY_ID, KEY_AIPCODE, KEY_ZIPCODE, KEY_CONDITION_NAME,
				KEY_ORDER_SUBTOTAL_FROM, KEY_ORDER_SUBTOTAL_TO, KEY_SHIPPING_PRICE,
				KEY_DELIVERY_COST, KEY_DELIVERY_TYPE  }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if(cursor !=null)
			cursor.moveToFirst();

		ShippingClass shippinginfo = new ShippingClass(cursor.getString(0),cursor.getString(1),
				cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),
				cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),
				cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13));

		return shippinginfo;
	}
	//get singlepincode Shipping
	public List<ShippingClass> getShippingInfoCharge(String price)
	{
		List<ShippingClass> list=new ArrayList<>();
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_FFHPSHIPPING, new String[] { KEY_ID, KEY_PK, KEY_WEBSITE_ID,
						KEY_COUNTRY, KEY_REGION_ID, KEY_CITY_ID, KEY_AIPCODE, KEY_ZIPCODE, KEY_CONDITION_NAME,
						KEY_ORDER_SUBTOTAL_FROM, KEY_ORDER_SUBTOTAL_TO, KEY_SHIPPING_PRICE,
						KEY_DELIVERY_COST, KEY_DELIVERY_TYPE  }, KEY_PRICE + "=?",
				new String[] { String.valueOf(price) }, null, null, null, null);
		try{
			if(cursor.moveToFirst())
			{
				do{
					ShippingClass shippinginfo=new ShippingClass();
					shippinginfo.setId(cursor.getString(0));
					shippinginfo.setPk(cursor.getString(1));
					shippinginfo.setWebsite_id(cursor.getString(2));
					shippinginfo.setDest_country_id(cursor.getString(3));
					shippinginfo.setDest_region_id(cursor.getString(4));
					shippinginfo.setDest_city(cursor.getString(5));
					shippinginfo.setDest_aip(cursor.getString(6));
					shippinginfo.setDest_zip_to(cursor.getString(7));
					shippinginfo.setCondition_name(cursor.getString(8));
					shippinginfo.setCondition_from_value(cursor.getString(9));
					shippinginfo.setCondition_to_value(cursor.getString(10));
					shippinginfo.setPrice(cursor.getString(11));
					shippinginfo.setCost(cursor.getString(12));
					shippinginfo.setDelivery_type(cursor.getString(13));

					list.add(shippinginfo);
				}while(cursor.moveToNext());
			}
		}catch (Exception e){

		}
		return list;
	}
	//get singlepincode Shipping
	public List<ShippingClass> getShippingInfoPincode(String pincode)
	{
		List<ShippingClass> list=new ArrayList<>();
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_FFHPSHIPPING, new String[] { KEY_ID, KEY_PK, KEY_WEBSITE_ID,
						KEY_COUNTRY, KEY_REGION_ID, KEY_CITY_ID, KEY_AIPCODE, KEY_ZIPCODE, KEY_CONDITION_NAME,
						KEY_ORDER_SUBTOTAL_FROM, KEY_ORDER_SUBTOTAL_TO, KEY_SHIPPING_PRICE,
						KEY_DELIVERY_COST, KEY_DELIVERY_TYPE  }, KEY_ZIPCODE + "=?",
				new String[] { String.valueOf(pincode) }, null, null, null, null);
		try{
			if(cursor.moveToFirst())
			{
				do{
					ShippingClass shippinginfo=new ShippingClass();
					shippinginfo.setId(cursor.getString(0));
					shippinginfo.setPk(cursor.getString(1));
					shippinginfo.setWebsite_id(cursor.getString(2));
					shippinginfo.setDest_country_id(cursor.getString(3));
					shippinginfo.setDest_region_id(cursor.getString(4));
					shippinginfo.setDest_city(cursor.getString(5));
					shippinginfo.setDest_aip(cursor.getString(6));
					shippinginfo.setDest_zip_to(cursor.getString(7));
					shippinginfo.setCondition_name(cursor.getString(8));
					shippinginfo.setCondition_from_value(cursor.getString(9));
					shippinginfo.setCondition_to_value(cursor.getString(10));
					shippinginfo.setPrice(cursor.getString(11));
					shippinginfo.setCost(cursor.getString(12));
					shippinginfo.setDelivery_type(cursor.getString(13));

					list.add(shippinginfo);
				}while(cursor.moveToNext());
			}
		}catch (Exception e){

		}
		return list;
	}
	//get list of Shipping
	public List<ShippingClass> getAllShipping()
	{
		List<ShippingClass> list=new ArrayList<>();
		try{
			String selectQuery="SELECT * FROM " + TABLE_FFHPSHIPPING;

			SQLiteDatabase db=this.getWritableDatabase();
			Cursor cursor=db.rawQuery(selectQuery, null);

			if(cursor.moveToFirst())
			{
				do{
					ShippingClass shippinginfo=new ShippingClass();
					shippinginfo.setId(cursor.getString(0));
					shippinginfo.setPk(cursor.getString(1));
					shippinginfo.setWebsite_id(cursor.getString(2));
					shippinginfo.setDest_country_id(cursor.getString(3));
					shippinginfo.setDest_region_id(cursor.getString(4));
					shippinginfo.setDest_city(cursor.getString(5));
					shippinginfo.setDest_aip(cursor.getString(6));
					shippinginfo.setDest_zip_to(cursor.getString(7));
					shippinginfo.setCondition_name(cursor.getString(8));
					shippinginfo.setCondition_from_value(cursor.getString(9));
					shippinginfo.setCondition_to_value(cursor.getString(10));
					shippinginfo.setPrice(cursor.getString(11));
					shippinginfo.setCost(cursor.getString(12));
					shippinginfo.setDelivery_type(cursor.getString(13));

					list.add(shippinginfo);
				}while(cursor.moveToNext());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	//update Shipping
	public int updateShipping(ShippingClass shippinginfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PK, shippinginfo.getPk());
		values.put(KEY_WEBSITE_ID, shippinginfo.getWebsite_id());
		values.put(KEY_COUNTRY, shippinginfo.getDest_country_id());
		values.put(KEY_REGION_ID, shippinginfo.getDest_region_id());
		values.put(KEY_CITY_ID, shippinginfo.getDest_city());
		values.put(KEY_AIPCODE, shippinginfo.getDest_aip());
		values.put(KEY_ZIPCODE, shippinginfo.getDest_zip_to());
		values.put(KEY_CONDITION_NAME, shippinginfo.getCondition_name());
		values.put(KEY_ORDER_SUBTOTAL_FROM, shippinginfo.getCondition_from_value());
		values.put(KEY_ORDER_SUBTOTAL_TO, shippinginfo.getCondition_to_value());
		values.put(KEY_SHIPPING_PRICE, shippinginfo.getPrice());
		values.put(KEY_DELIVERY_COST, shippinginfo.getCost());
		values.put(KEY_DELIVERY_TYPE, shippinginfo.getDelivery_type());

		return db.update(TABLE_FFHPSHIPPING, values, KEY_ID + " = ?",
				new String[] { String.valueOf(shippinginfo.getId()) });
	}
	// Deleting single Shipping
	public void deleteShipping(ShippingClass shippinginfo) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FFHPSHIPPING, KEY_ID + " = ?",
				new String[] { String.valueOf(shippinginfo.getId()) });
		db.close();
	}
	// Deleting All Shipping
	public void deleteAllShipping() {
		SQLiteDatabase db = this.getWritableDatabase();
		try{
			db.delete(TABLE_FFHPSHIPPING, null, null);
			db.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			db.close();
		}
	}
	// Getting Shipping Count
	public int getShippingCount() {
		String countQuery = "SELECT  * FROM " + TABLE_FFHPSHIPPING;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();
		// return count
		return cursor.getCount();
	}*/
}
