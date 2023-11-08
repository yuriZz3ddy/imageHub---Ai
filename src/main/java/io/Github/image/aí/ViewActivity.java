package io.Github.image.ai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Button;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ViewActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private boolean toggleView = false;
	private HashMap<String, Object> MapBot = new HashMap<>();
	private HashMap<String, Object> MapValueBot = new HashMap<>();
	private boolean messageLog = false;
	
	private ArrayList<HashMap<String, Object>> MapListBot = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> MapListValueBot = new ArrayList<>();
	
	private LinearLayout linear2;
	private ImageView imageview1;
	private ProgressBar progressbar1;
	private LinearLayout linear3;
	private Button button2;
	private LinearLayout linear4;
	private Button button3;
	
	private Intent LinkView = new Intent();
	private RequestNetwork downloadTZ_JK;
	private RequestNetwork.RequestListener _downloadTZ_JK_request_listener;
	private Calendar dateU0 = Calendar.getInstance();
	private Calendar dateUp = Calendar.getInstance();
	private TimerTask clica;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.view);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		button2 = (Button) findViewById(R.id.button2);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		button3 = (Button) findViewById(R.id.button3);
		downloadTZ_JK = new RequestNetwork(this);
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!toggleView) {
					clica = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									LinkView.setAction(Intent.ACTION_VIEW);
									LinkView.setData(Uri.parse(getIntent().getStringExtra("url")));
									toggleView = false;
									startActivity(LinkView);
								}
							});
						}
					};
					_timer.schedule(clica, (int)(999));
					toggleView = true;
					_MessageToast("carregando...");
				}
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!messageLog) {
					MapBot = new HashMap<>();
					MapBot.put("username","imageHub - Ai");
					if (MapBot.containsValue("")) {
						MapBot.remove("username");
					}
					MapBot.put("avatar_url","https://cdn.discordapp.com/attachments/1050227209383706688/1164011336552947732/20231017_212445.png?ex=654ae388&is=65386e88&hm=d43d6f8513a6700f815481fe844158e8a82d22c5b983a2f1fa1335793003daf8&.jpg");
					if (MapBot.containsValue("")) {
						MapBot.remove("avatar_url");
					}
					MapBot.put("embeds",MapListBot);
					{
						HashMap<String, Object> _embeds = new HashMap<>();
						_embeds.put("title","imagens salvar");
						if (_embeds.containsValue("")) {
							_embeds.remove("title");
						}
						_embeds.put("image",MapValueBot);
						MapValueBot.put("url",getIntent().getStringExtra("url"));
						if (MapValueBot.containsValue("")) {
							_embeds.remove("image");
						}
						MapValueBot = new HashMap<>();
						_embeds.put("color",(int)(7419530));
						if (_embeds.containsValue((int)0) || _embeds.containsValue("")) {
							_embeds.remove("color");
						}
						
						if (!(_embeds.containsKey("title") || _embeds.containsKey("description") || _embeds.containsKey("image") || _embeds.containsKey("thumbnail") || _embeds.containsKey("author") || _embeds.containsKey("footer") || _embeds.containsKey("fields"))) {
							_embeds.remove("embeds");
							_embeds.remove("color");
						}
						MapListBot.add((int)0, _embeds);
					}
					if (!(MapBot.get("embeds").toString().length() > 4)) {
						MapBot.remove("embeds");
					}
					downloadTZ_JK.setParams(MapBot, RequestNetworkController.REQUEST_BODY);
					downloadTZ_JK.startRequestNetwork(RequestNetworkController.POST, "https://discord.com/api/webhooks/1167185633719103508/UAIpIZ3_T_-Lw7Ze-SHDxwsd5UoGooXSRJ_pL_ykSy0Ojcoa3kw_eAsBBEPYCUHqBTe5","", _downloadTZ_JK_request_listener);
					progressbar1.setVisibility(View.VISIBLE);
					messageLog = true;
				}
			}
		});
		
		_downloadTZ_JK_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				progressbar1.setVisibility(View.GONE);
				MapListBot.clear();
				MapBot.clear();
				MapListValueBot.clear();
				MapValueBot.clear();
				messageLog = false;
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("url"))).into(imageview1);
		progressbar1.setVisibility(View.GONE);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _MessageToast (final String _Text) {
		Toast FLToast = Toast.makeText(getApplicationContext(), _Text, Toast.LENGTH_SHORT);
		FLToast.show();
	}
	
	
	public void _alertMods () {
		
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