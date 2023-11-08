package io.Github.image.ai;

import io.Github.image.ai.ZFileActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.Button;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends  AppCompatActivity  { 
	
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> MapBot = new HashMap<>();
	private HashMap<String, Object> Map_img = new HashMap<>();
	private HashMap<String, Object> codeKey_ai = new HashMap<>();
	private String Sd = "";
	private String Sd2 = "";
	private HashMap<String, Object> keyAi = new HashMap<>();
	private boolean backKey = false;
	private String AiUrl = "";
	private boolean toggle = false;
	private String codeView = "";
	private boolean save = false;
	private boolean UrlToggle = false;
	private String token = "";
	private HashMap<String, Object> codeKey_ai_2 = new HashMap<>();
	private String Xf = "";
	private String Xf2 = "";
	
	private ArrayList<String> ERL = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> Jp = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> log_i = new ArrayList<>();
	
	private LinearLayout backgroundView1;
	private GridView gridview1;
	private TextView textview1;
	private ProgressBar progressbar1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private EditText seachView;
	private LinearLayout linear3;
	private Button open1;
	
	private RequestNetwork seaArt;
	private RequestNetwork.RequestListener _seaArt_request_listener;
	private Intent image_open = new Intent();
	private RequestNetwork load_TZ;
	private RequestNetwork.RequestListener _load_TZ_request_listener;
	private RequestNetwork ServerJoin;
	private RequestNetwork.RequestListener _ServerJoin_request_listener;
	private AlertDialog.Builder ShowDialog_T;
	private Intent linkUrl = new Intent();
	private AlertDialog.Builder CodeJson;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		backgroundView1 = (LinearLayout) findViewById(R.id.backgroundView1);
		gridview1 = (GridView) findViewById(R.id.gridview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		seachView = (EditText) findViewById(R.id.seachView);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		open1 = (Button) findViewById(R.id.open1);
		seaArt = new RequestNetwork(this);
		load_TZ = new RequestNetwork(this);
		ServerJoin = new RequestNetwork(this);
		ShowDialog_T = new AlertDialog.Builder(this);
		CodeJson = new AlertDialog.Builder(this);
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (Jp.get((int)_position).containsKey("cover")) {
					if (!Jp.get((int)_position).get("cover").toString().equals("")) {
						image_open.setClass(getApplicationContext(), ViewActivity.class);
						image_open.putExtra("url", Jp.get((int)_position).get("cover").toString());
						startActivity(image_open);
						backKey = true;
					}
					else {
						_MessageToast("Lá não, found");
					}
				}
			}
		});
		
		open1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!seachView.getText().toString().equals("")) {
					MapBot = new HashMap<>();
					MapBot.put("Token", token);
					MapBot.put("Content-Type", "application/json");
					seaArt.setHeaders(MapBot);
					Map_img.put("offset", (int)(0));
					Map_img.put("page", (int)(1));
					Map_img.put("page_size", (int)(60));
					Map_img.put("url", "");
					Map_img.put("keyword", seachView.getText().toString());
					Map_img.put("order_by", "hot");
					seaArt.setParams(Map_img, RequestNetworkController.REQUEST_BODY);
					seaArt.startRequestNetwork(RequestNetworkController.POST, "https://www.seaart.ai/api/v1/square/list", "", _seaArt_request_listener);
					gridview1.setAdapter(new Gridview1Adapter(Jp));
					progressbar1.setVisibility(View.VISIBLE);
					seachView.setText("");
					codeView = "";
					backKey = true;
					Jp.clear();
				}
				else {
					_MessageToast("Já search normal");
				}
			}
		});
		
		_seaArt_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				try{
					codeKey_ai = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
					Sd = (new Gson()).toJson(codeKey_ai.get("data"), new TypeToken<HashMap<String, Object>>(){}.getType());
					codeKey_ai = new Gson().fromJson(Sd, new TypeToken<HashMap<String, Object>>(){}.getType());
					Sd2 = (new Gson()).toJson(codeKey_ai.get("items"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					Jp = new Gson().fromJson(Sd2, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					if (Jp.get((int)0).containsKey("cover")) {
						gridview1.setAdapter(new Gridview1Adapter(Jp));
						progressbar1.setVisibility(View.GONE);
						codeView = _response;
					}
				}catch(Exception e){
					 
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_load_TZ_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_ServerJoin_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				log_i = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				_alertMods();
				ShowDialog_T.setTitle("Discord suporte");
				ShowDialog_T.setMessage("versão atualize agora");
				ShowDialog_T.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						linkUrl.setAction(Intent.ACTION_VIEW);
						linkUrl.setData(Uri.parse(log_i.get((int)0).get("server").toString()));
						_MessageToast("Link Joinha wait...");
						startActivity(linkUrl);
					}
				});
				ShowDialog_T.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				ShowDialog_T.setCancelable(false);
				AlertDialog alertL = ShowDialog_T.show();
				android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
				gd.setColor(0xFF000000);
				gd.setCornerRadius((int)15);
				gd.setStroke((int)3,0xFF7B1FA2);
				alertL.getWindow().setBackgroundDrawable(gd);
				alertL.show();
				toggle = false;
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		progressbar1.setVisibility(View.GONE);
		textview1.setVisibility(View.GONE);
		seachView.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1, 0xFF9C27B0, 0xFF212121));
		open1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)10, (int)0, Color.TRANSPARENT, 0xFF212121));
		gridview1.setNumColumns((int)3);
		_TokenCode();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (backKey) {
			backKey = false;
			try{
				gridview1.setAdapter(new Gridview1Adapter(Jp));
				codeView = "";
				Jp.clear();
				_MessageToast("Back !");
			}catch(Exception e){
				image_open.setClass(getApplicationContext(), MainActivity.class);
				startActivity(image_open);
				_MessageToast("restart Back !");
				finish();
			}
		}
		else {
			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		menu.add(0, 1, 0, "Discord Server");
		menu.add(0, 2, 0, "alternar código");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
		public boolean onOptionsItemSelected(MenuItem item){
		final int _id = item.getItemId();
		final String _title = (String) item.getTitle();
		if (_id == 1) {
			if (!toggle) {
				toggle = true;
				ServerJoin.startRequestNetwork(RequestNetworkController.GET, "https://pastebin.com/raw/LALfGg6r", "", _ServerJoin_request_listener);
				if (!save) {
					save = true;
					_MessageToast("carregando wait...");
				}
			}
		}
		if (_id == 2) {
			if (!codeView.equals("")) {
				_alertMods();
				CodeJson.setTitle("código Json");
				CodeJson.setMessage(codeView);
				CodeJson.setPositiveButton("sair", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				CodeJson.setCancelable(false);
				AlertDialog alertL = CodeJson.show();
				android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
				gd.setColor(0xFF000000);
				gd.setCornerRadius((int)15);
				gd.setStroke((int)3,0xFF6A1B9A);
				alertL.getWindow().setBackgroundDrawable(gd);
				alertL.show();
			}
			else {
				_MessageToast("No clear, Json");
			}
		}
		return super.onOptionsItemSelected(item);
	}
	public void _MessageToast (final String _Text) {
		Toast FLToast = Toast.makeText(getApplicationContext(), _Text, Toast.LENGTH_SHORT);
		FLToast.show();
	}
	
	
	public void _alertMods () {
		ShowDialog_T = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
		CodeJson = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
	}
	
	
	public void _fixx () {
	}
	
	
	public void _TokenCode () {
		token = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzZWEtYXJ0IiwiYXVkIjpbImxvZ2luIl0sImV4cCI6MTY5ODg2Nzg2NywiaWF0IjoxNjk4MjYzMDY3LCJqdGkiOiIyMDg4MDA1NDE4ODc3MTMzMyIsInBheWxvYWQiOnsiaWQiOiJmODgwYjEzYjY1ZTQyYzE1OTc5NjhiZjYzNjAwOTQ5ZiIsImVtYWlsIjoieXVyaS5mcmVkZHkxMkBnbWFpbC5jb20iLCJjcmVhdGVfYXQiOjE2OTY4MjY2MzYxMDUsInN0YXR1cyI6MX19.M5TkzivqLRcHcG2lxlLymfKdEJtKnUO3LBca_m26voyzG1UsBaMY4v0MjkHeBw-8nMyeLD482RHWLYdKGFF8knsjvrFh5X5__cai2iG5MgaVjPGuXghnSVDicV7bpbSxv1ipGDTKjukdWZoHydE7E4hxfnKRm4bagJQ2E6FJi8WAYqCdxR_TJNIcZehIkjeikEyjMtXAaD8qG1odCUrrOyEBwklBQC0OQ5blaI7aRLL-Kq-hgj5Bnsb99kwonWB1JpekRxYlgMrbv4f0L8l457BX365JuaLTtkCT43a_THGpV8W3qzj6eiJaZur2of02ky8_gF5OlDqlzMB5m9qV_w";
	}
	
	
	public class Gridview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.image, null);
			}
			
			final androidx.cardview.widget.CardView imageView_L = (androidx.cardview.widget.CardView) _view.findViewById(R.id.imageView_L);
			final androidx.cardview.widget.CardView cardview2 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview2);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView image_Ai = (ImageView) _view.findViewById(R.id.image_Ai);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			
			if (_data.get((int)_position).containsKey("cover")) {
				if (!_data.get((int)_position).get("cover").toString().equals("")) {
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("cover").toString())).into(image_Ai);
					cardview2.setVisibility(View.GONE);
				}
				else {
					imageView_L.setVisibility(View.GONE);
					cardview2.setVisibility(View.VISIBLE);
				}
			}
			imageView_L.setCardBackgroundColor(0xFF000000);
			imageView_L.setRadius((float)20);
			cardview2.setCardBackgroundColor(0xFF212121);
			cardview2.setRadius((float)20);
			textview1.setText("NO !");
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}