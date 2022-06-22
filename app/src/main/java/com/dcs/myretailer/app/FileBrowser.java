/* MIT License

Copyright (c) 2017 icebahamut

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */

package com.dcs.myretailer.app;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Build;
//import android.os.Environment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileBrowser extends Dialog implements DialogInterface {

	public interface OnFileOKListener{
		public void onSelected(View v, File file);
	}

	private Button btn_ok;
	private Button btn_cancel;
	private ImageButton btn_updir;
	private EditText et_filename;
	private ArrayAdapter<String> adapt_path;
	private Spinner spn_path;
	
	private ListView lst_files;
	private LinearLayout lay_progress;
	private TextView txt_nofiles;
	private ProgressBar pg_load;
	private FileListAdapter files;
	private boolean isSave = false;
	private String currentPath = "";
	private List<String[]> fileext = new ArrayList<String[]>();
	//private int selectedExtension = 0;
	
	private long clicktimer = 0;
	
	private ArrayAdapter<String> adapt_type;
	private Spinner spn_fileext;
	
	private String dialogtitle = "";
	
	private int fileselectpos = -1;
	
	private OnFileOKListener listener;
	private java.text.DateFormat fmtdate = null;
	private java.text.DateFormat fmttime = null;
	
	private boolean running = false;
	private Dialog dialog = this;
	
	private List<FileExtension> MIMEextension = new ArrayList<FileExtension>();
	private Resources res;
	
	private AdapterView.OnItemSelectedListener pathchange = new AdapterView.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
			if(position!=-1){
				ChangeDir(adapt_path.getItem(position));
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {}
	};
	
	
	/**
     * Create a File Browser Dialog, and use showOpenDialog() or showSaveDialog() to show File Browser dialog.
     * 
     * @param context The Context the Dialog is to run it.  In particular, it
     *                uses the window manager and theme in this context to
     *                present its UI.
     */
	public FileBrowser(Context context) {
		super(context);
		listener = null;
		
		setContentView(R.layout.dlg_filebrowser);
		fmtdate = android.text.format.DateFormat.getDateFormat(getContext());
		fmttime = android.text.format.DateFormat.getTimeFormat(getContext());
		res = getContext().getResources();
		try {
			LoadExtensionIcon(Environment.getDataDirectory().getAbsolutePath()+"/icon/");
		} catch (IOException e) {
			Log.e("ERROR", e.toString());
		}
		try {
			LoadExtensionIcon(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"filebrowser/icon/");
		} catch (IOException e) {
			Log.e("ERROR", e.toString());
		}

		lay_progress = (LinearLayout)findViewById(R.id.lay_indicator);
		pg_load = (ProgressBar)findViewById(R.id.pg_load);
		txt_nofiles = (TextView)findViewById(R.id.txt_ind_nofiles);
		
		spn_path = (Spinner)findViewById(R.id.spn_path);
		adapt_path = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item);
		adapt_path.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn_path.setAdapter(adapt_path);
		
		
		spn_path.setOnItemSelectedListener(pathchange);

		
		spn_fileext = (Spinner)findViewById(R.id.spn_filetype);
		
		adapt_type = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item);
		adapt_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spn_fileext.setAdapter(adapt_type);
		
		btn_ok = (Button)findViewById(R.id.btn_ok);
		btn_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final View view = arg0;
				String filename = et_filename.getText().toString();
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
					if(filename.isEmpty()){
						if(isSave){
							Toast.makeText(view.getContext(), res.getString(R.string.str_emptyfilename), Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(view.getContext(), res.getString(R.string.str_noselected), Toast.LENGTH_SHORT).show();
						}
						return;
					}
				}
				File selected = new File(currentPath+"/"+filename);
				if(selected.isDirectory()){
					ChangeDir(selected.getAbsolutePath());
					return;
				}
				if(isSave){
					/*
					if(fileext.size()>0){
						if(spn_fileext.getSelectedItemPosition()!=Spinner.INVALID_POSITION){
							
							String[] exts = fileext.get(spn_fileext.getSelectedItemPosition());
							if(exts.length>0){
								boolean gotextension = false;
								for(int i=0;i<exts.length;i++){
									String e = "";
									if(!exts[i].startsWith(".")){
										e = "."+exts[i];
									}
									e = e.toLowerCase();
									if(e.contains("*")){
										continue;
									}
									if(filename.toLowerCase().endsWith(e)){
										gotextension = true;
										break;
									}
								}
								
								if(!gotextension){//the save filename doesn't include extension so we added in
									String e = "";
									if(!exts[0].startsWith(".")){
										e = "."+exts[0];
									}
									e = e.toLowerCase();
									if(!e.contains("*")){
										selected = new File(currentPath+"/"+filename+e);
									}
								}
							}
						}
					}
					*/
					
					
					if(!(selected.getParentFile().canWrite() && selected.getParentFile().canRead())){
						Toast.makeText(view.getContext(), res.getString(R.string.str_dircantsave), Toast.LENGTH_SHORT).show();
						return;
					}
					if(selected.exists()){
						final File _selected = selected;
						AlertDialog.Builder dlg = new AlertDialog.Builder(arg0.getContext());
						dlg.setTitle(res.getString(R.string.str_titleoverwrite));
						dlg.setCancelable(false);
						dlg.setMessage(String.format(res.getString(R.string.str_overwrite_ask), selected.getName()));
						dlg.setNegativeButton(res.getString(R.string.str_cancel), null);
						dlg.setPositiveButton(res.getString(R.string.str_yes), new OnClickListener(){
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								if(listener!=null){
									listener.onSelected(view, _selected);
								}
								dialog.dismiss();
							}
						});
						dlg.show();
					}else{
						if(listener!=null){
							listener.onSelected(view, selected);
						}
						dialog.dismiss();
					}
				}else{
					if(!selected.exists()){
						Toast.makeText(view.getContext(), String.format(res.getString(R.string.str_filenotexist), selected.getName()), Toast.LENGTH_SHORT).show();
						return;
					}else{
						if(!selected.getParentFile().canRead()){
							Toast.makeText(view.getContext(), res.getString(R.string.str_nopermission), Toast.LENGTH_SHORT).show();
							return;
						}
						if(listener!=null){
							listener.onSelected(view, selected);
						}
						dialog.dismiss();
					}
				}
			}
		});
		
		btn_cancel = (Button)findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
				listener = null;
			}
			
		});
		btn_updir = (ImageButton)findViewById(R.id.btn_updir);
		et_filename = (EditText)findViewById(R.id.ed_filename);
		if(isSave){
			et_filename.setVisibility(View.VISIBLE);
		}else{
			et_filename.setVisibility(View.GONE);
		}
		
		lst_files = (ListView)findViewById(R.id.lst_filelist);
		List<File> listfiles = new ArrayList<File>();
		lst_files.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		files = new FileListAdapter(this.getContext(), listfiles);
		files.notifyDataSetChanged();
		lst_files.setAdapter(files);
		
		
		btn_updir.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(!running)ChangeDir(new File(currentPath).getParent());
			}
		});
		
		
		lst_files.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				final View v = view;
				fileselectpos = position;
				if(position!=-1){
					et_filename.setText(files.getItem(position).getName());
					if(System.currentTimeMillis()<clicktimer){
						final File f=files.getItem(position);
						if(f.isDirectory()){
							ChangeDir(files.getItem(position).getAbsolutePath());
						}else{
							if(isSave){
								if(f.exists()){
									AlertDialog.Builder dlg = new AlertDialog.Builder(arg0.getContext());
									dlg.setTitle(res.getString(R.string.str_titleoverwrite));
									dlg.setCancelable(false);
									dlg.setMessage(String.format(res.getString(R.string.str_overwrite_ask), f.getName()));
									dlg.setNegativeButton(res.getString(R.string.str_cancel), null);
									dlg.setPositiveButton(res.getString(R.string.str_yes), new OnClickListener(){
										@Override
										public void onClick(DialogInterface arg0, int arg1) {
											if(listener!=null){
												listener.onSelected(v, f);
											}
											dialog.dismiss();
										}
									});
									dlg.show();
								}
							}else{
								if(!f.exists()){
									Toast.makeText(view.getContext(), String.format(res.getString(R.string.str_filenotexist), f.getName()), Toast.LENGTH_SHORT).show();
									return;
								}else{
									if(listener!=null){
										listener.onSelected(v, f);
									}
									dialog.dismiss();
								}
							}
						}
					}
					clicktimer = System.currentTimeMillis()+500;
				}
				
			}

			
		});
		
		spn_fileext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				ChangeDir(currentPath);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}

			
		});
		
		ImageButton btn_del = (ImageButton)findViewById(R.id.btn_del);
		btn_del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				final Context context = arg0.getContext();
				
				if(fileselectpos==-1){
					Toast.makeText(context, res.getString(R.string.str_noselected), Toast.LENGTH_SHORT).show();
					return;
				}
				
				final File file = files.getItem(fileselectpos);
				
				final AlertDialog.Builder dlg = new AlertDialog.Builder(context);
				dlg.setTitle(res.getString(R.string.str_delete_title));
				
				dlg.setMessage(String.format(res.getString(R.string.str_delete_ask), file.getName()));
				dlg.setNegativeButton(res.getString(R.string.str_no), null);
				dlg.setPositiveButton(res.getString(R.string.str_yes), new OnClickListener(){

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if(deleteFile(file)){
							
							Toast.makeText(context, String.format(res.getString(R.string.str_delete_ok), file.getName()), Toast.LENGTH_SHORT).show();
							ChangeDir(currentPath);
						}else{
							Toast.makeText(context, res.getString(R.string.str_delete_err), Toast.LENGTH_SHORT).show();
						}
					}

				});
				dlg.show();
				
			}
		});
		
		ImageButton btn_rename = (ImageButton)findViewById(R.id.btn_rename);
		btn_rename.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
					final Context context = arg0.getContext();
					
					if(fileselectpos==-1){
						Toast.makeText(context, res.getString(R.string.str_noselected), Toast.LENGTH_SHORT).show();
						return;
					}

					final File file = files.getItem(fileselectpos);
					final AlertDialog.Builder dlg = new AlertDialog.Builder(context);
					
					dlg.setTitle(res.getString(R.string.str_rename_title));
					final EditText input = new EditText(context);
					input.setText(file.getName());
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.MATCH_PARENT);
					input.setLayoutParams(lp);
					dlg.setView(input);
					
					dlg.setNegativeButton(res.getString(R.string.str_cancel), null);
					dlg.setPositiveButton(res.getString(R.string.str_ok), new OnClickListener(){
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if(input.getText().length()==0){
								Toast.makeText(context, res.getString(R.string.str_emptyfilename), Toast.LENGTH_SHORT).show();
								return;
							}
							
							if(input.toString().equals(file.getName())){
								ChangeDir(currentPath);
								return;
							}
							
							
							File newname = new File(currentPath + "/"+input.getText().toString());
							
							if(newname.exists()){
								
								Toast.makeText(context, res.getString(R.string.str_samefilename), Toast.LENGTH_SHORT).show();
								return;
							}else{
								file.renameTo(newname);
								if(newname.exists() && !file.exists()){
									
									Toast.makeText(context, String.format(res.getString(R.string.str_rename_ok),newname.getName()), Toast.LENGTH_SHORT).show();
									ChangeDir(currentPath);
									return;
								}else{
									Toast.makeText(context, res.getString(R.string.str_rename_err), Toast.LENGTH_SHORT).show();
									return;
								}
								
							}
						}
						
					});
					dlg.show();
				
			}
		});
		
		ImageButton btn_mkdir = (ImageButton)findViewById(R.id.btn_mkdir);
		btn_mkdir.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				if(new File(currentPath).canWrite()){
					final Context context = arg0.getContext();
					final AlertDialog.Builder dlg = new AlertDialog.Builder(context);
					
					dlg.setTitle(res.getString(R.string.str_mkdir_title));
					final EditText input = new EditText(context);  
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.MATCH_PARENT);
					input.setLayoutParams(lp);
					dlg.setView(input);
					
					dlg.setNegativeButton(res.getString(R.string.str_cancel), null);
					dlg.setPositiveButton(res.getString(R.string.str_ok), new OnClickListener(){
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if(input.getText().length()==0){
								Toast.makeText(context, res.getString(R.string.str_emptyfilename), Toast.LENGTH_SHORT).show();
								return;
							}
							
							File dir = new File(currentPath + "/"+input.getText().toString());
							if(dir.exists()){
								Toast.makeText(context, res.getString(R.string.str_samefilename), Toast.LENGTH_SHORT).show();
								return;
							}else{
								if(!dir.mkdir()){
									Toast.makeText(context, res.getString(R.string.str_mkdir_err2), Toast.LENGTH_SHORT).show();
									return;
								}else{
									
									Toast.makeText(context, String.format(res.getString(R.string.str_mkdir_ok), dir.getName()), Toast.LENGTH_SHORT).show();
									ChangeDir(currentPath);
									return;
								}
							}
						}
						
					});
					dlg.show();
				}else{
					Toast.makeText(arg0.getContext(), res.getString(R.string.str_mkdir_err), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	private boolean deleteFile(File file){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f:files){
				if(!deleteFile(f)){
					return false;
				}
			}
			return file.delete();
		}else{
			if(file.delete()){
				return true;
				//Toast.makeText(context, "Deleted "+file.getName()+"!", Toast.LENGTH_SHORT).show();
				//ChangeDir(currentPath);
			}else{
				return false;
				//Toast.makeText(context, "Failed to delete or no permission!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
     * Load File Extension Icon image list from param.txt<br />
     * The <b>param.txt</b> file is a simply text file where each line consist of the file extensions pointing to the icon image<br />
     * The format is extension list followed by the vertical bar(|) and the image file<br />
     * The image file must be same location with the param.txt<br />
     * Example of the param.txt content:<br />
     * jpg;jpeg|icon_jpg.png<br />
     * txt|notepad.png
     * @param path The location to the directory where param.txt located<br />
     * @exception IOException Failed to open file extension param.txt file
     */
	public void LoadExtensionIcon(String path) throws IOException{
		//String iconpath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"filebrowser/icon/";
		String iconpath = path;

		if(new File(iconpath+"/param.txt").exists()){
			BufferedReader br = new BufferedReader(new FileReader(iconpath+"/param.txt"));
			String line = "";
			while((line=br.readLine())!=null){
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
					if(line.isEmpty()){
						continue;
					}
				}
				String[] split = line.split("\\|");
				if(split.length==2){
					String[] exts = split[0].split(";");
					Bitmap b = BitmapFactory.decodeFile(iconpath+"/"+split[1]);
					MIMEextension.add(new FileExtension(exts,b));
				}
			}
		}
		
	}
	
	/**
     * Show Save Dialog
     */
	public void ShowSaveDialog(){
		isSave = true;
		et_filename.setVisibility(View.VISIBLE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			if(currentPath.isEmpty()){
				currentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			}
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			if(dialogtitle.isEmpty()){
				super.setTitle(res.getString(R.string.str_savedialog));
			}else{
				super.setTitle(dialogtitle);
			}
		}
		super.show();
		ChangeDir(currentPath);
	}
	
	/**
     * Show Open Dialog
     */
	public void ShowOpenDialog(){
		Log.i("Hello","HERERE");
		isSave = false;
		et_filename.setVisibility(View.GONE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			if(currentPath.isEmpty()){
				currentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
			}
		}


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			if(dialogtitle.isEmpty()){
				setTitle(res.getString(R.string.str_opendialog));
			}else{
				setTitle(dialogtitle);
			}
		}
		show();
		ChangeDir(currentPath);
	}
	
	
	/**
     * Set file extension to filter out the file list when displaying the file browser.<br />
     * The format for the file extension is the description of the file extension, followed by the vertical bar(|) and the file extension pattern.<br />
     * Example of file extension format:<br />
     * Text Files (*.txt)|txt<br /><br />
     * You can add multiple file extension to create chaining list, example:<br />
     * Image Files(*.jpg;*.png)|png;jpg;jpeg|PNG Image(*.png)|png|JPEG|jpeg;jpg|All Files(*.*)|*.*<br />
     * @param fileext The file extension filter list<br />
     * @exception IllegalArgumentException File extension format is invalid
     */
	public void SetFileExtension(String fileext) throws IllegalArgumentException{

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			if(fileext.isEmpty()){
				this.fileext.clear();
				adapt_type.clear();
				spn_fileext.setSelection(-1);
			}else{
				List<String> _fileext_name = new ArrayList<String>();
				List<String[]> _fileext = new ArrayList<String[]>();
				String[] splitfileext = fileext.split("\\|");
				if(splitfileext.length%2 != 0){
					throw new IllegalArgumentException("Invalid File extension!");
				}

				for(int i=0;i<splitfileext.length;i+=2){
					_fileext_name.add(splitfileext[i]);
					String[] ext = splitfileext[i+1].split("\\;");
					for(int j=0;j<ext.length;j++){
						if(!ext[j].startsWith(".")){
							ext[j] = "."+ext[j];
						}
					}
					_fileext.add(ext);
				}

				this.fileext.addAll(_fileext);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
					adapt_type.addAll(_fileext_name);
				}
				spn_fileext.setSelection(0);
			}
		}
	}
	
	private void ChangeDir(String dirpath){
		//lst_files.setSelection(-1);
		
		if(running){
			return;
		}
		
		

		lst_files.clearChoices();
		lst_files.requestLayout();
		fileselectpos = -1;
		if(dirpath==null){
			return;
		}

		lay_progress.setVisibility(View.VISIBLE);
		pg_load.setVisibility(View.VISIBLE);
		txt_nofiles.setVisibility(View.GONE);
		lst_files.setVisibility(View.GONE);
		final String path = dirpath;
		Runnable runthread = new Runnable(){
			@Override
			public void run() {
				running = true;
				File f = new File(path);

				if(f.canRead()){
					if(f.isDirectory()){
						List<File> dirfiles = new ArrayList<File>();
						File[] _dirfiles = f.listFiles();

						String[] ext = null;
						if(spn_fileext.getSelectedItemPosition()!=-1){
							ext = fileext.get(spn_fileext.getSelectedItemPosition());
						}

						for(File _f:_dirfiles){
							if(_f.isDirectory()){
								dirfiles.add(_f);
							}else{
								if(ext==null){
									dirfiles.add(_f);
								}else{
									for(String extension:ext){
										String pattern = extension.replace("*", ".*");
										if(_f.getName().toLowerCase().matches(pattern)){
											dirfiles.add(_f);
										}
									}
								}
							}
						}
						
						List<File> paths=dirfiles;
						final List<File> dir = new ArrayList<File>();
						final List<File> file = new ArrayList<File>();
						java.util.Collections.sort(paths);
						for(File _f:paths){
							if(_f.isDirectory()){
								dir.add(_f);
							}else{
								file.add(_f);
							}
						}

						lst_files.post(new Runnable(){
							@Override
							public void run() {
								files.clear();
								if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
									files.addAll(dir);
									files.addAll(file);
								}
								dir.clear();
								file.clear();
							}
						});
						


					}
				}else{
					lst_files.post(new Runnable(){
						@Override
						public void run() {
							files.clear();
						}
					});
				}
				
				
				currentPath = f.getAbsolutePath();
				spn_path.post(new Runnable(){
					@Override
					public void run() {
						spn_path.setOnItemSelectedListener(null);
						spn_path.post(new Runnable(){
							@Override
							public void run() {
								int existpos = -1;
								for(int i=0;i<adapt_path.getCount();i++){
									if(adapt_path.getItem(i).equals(currentPath)) existpos = i;
								}
								//spn_path.setTag(true);
								if(existpos==-1){
									adapt_path.insert(currentPath,0);
									spn_path.setSelection(0);

								}else{
									spn_path.setSelection(existpos);
								}
								et_filename.setText("");
								lst_files.setSelection(0);
								if(lst_files.getCount()>0){
									lay_progress.setVisibility(View.GONE);
									lst_files.setVisibility(View.VISIBLE);
								}else{
									pg_load.setVisibility(View.GONE);
									txt_nofiles.setVisibility(View.VISIBLE);
								}
								spn_path.post(new Runnable(){
									@Override
									public void run() {
										spn_path.setOnItemSelectedListener(pathchange);
									}
								});
							}
						});
					}
				});
				
				running = false;
			}
		};
		new Thread(runthread).start();
		
	}
	
	
	/**
     * Set the default start location when showOpenDialog() or showSaveDialog() open dialogs
     * @param filedir The directory to the default start location.<br />
     */
	public void setDefaultDir(String filedir){
		currentPath = filedir;
	}
	
	/**
     * Set OK button text
     * @param text The OK button text<br />
     */
	public void setOKButtonText(String text){
		btn_ok.setText(text);
	}
	
	/**
     * Set File Browser dialog title<br />
     * If the the parameter is empty string, it will show "Open..." when showOpenDialog() called or "Save..." when showSaveDialog() called
     * @param title File Browser dialog title<br />
     */
	@Override
	public void setTitle(CharSequence title){
		dialogtitle = title.toString();
		super.setTitle(title);
	}
	
	/**
     * Set Cancel button text
     * @param text The Cancel button text<br />
     */
	public void setCancelButtonText(String text){
		btn_cancel.setText(text);
	}
	
	/**
     * Set the OK button event<br />
     * Occurs when the user pressed OK button on the File Browser dialog
     * @param listener The OnFileOKListener event<br />
     */
	public void setOnFileOKListener(OnFileOKListener listener){
		this.listener = listener;
	}
	
	private class FileExtension{
		private String[] extension = null;
		private Bitmap iconimg = null;
		public FileExtension(String[] ext, Bitmap icon){
			extension = ext;
			iconimg = icon;
		}
	}
	
	private String getExtension(String filename){
		int length = filename.length();
		int num = length-1;
		while (num >= 0) {
			if (filename.charAt(num) == '.') {
				return filename.substring(num+1);
			}
			num--;
		}
		return "";
	}
	
	private class FileListAdapter extends ArrayAdapter<File> {
	    public FileListAdapter(Context context, List<File> files) {
	       super(context, 0, files);
	    }
	    
	    public FileListAdapter(Context context,int resource, int textViewResourceId, List<File> files) {
	    	super(context,resource,textViewResourceId, files);
	    }


	    public View getView(int position, View convertView, ViewGroup parent) {
	    	//File f = getItem(position);
	    	if (convertView == null) {
	    		convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_filelist, parent, false);
	    	}
//	    	IconViewer icon = (IconViewer) convertView.findViewById(R.id.tvicon);
	    	TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
	    	TextView tvSize = (TextView) convertView.findViewById(R.id.tvSize);
	    	TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
	    	File file = getItem(position);
	    	Date dt = new Date(file.lastModified());
	    	tvDate.setText(fmtdate.format(dt) + " "+fmttime.format(dt));
	    	tvName.setText(file.getName());
	    	if(file.isDirectory()){
	    		Bitmap b = BitmapFactory.decodeResource(res, R.drawable.ic_folder);
	    		//icon.setIcon(b);
	    		int filecount = 0;
	    		if(file.list()!=null){
	    			filecount = file.list().length;
	    		}
	    		
		    	tvSize.setText(String.format(res.getString(R.string.str_filecount), filecount));
	    	}else{
	    		//String ext = android.webkit.MimeTypeMap.getFileExtensionFromUrl(file.getName());
	    		String ext = getExtension(file.getName());
	    		Bitmap b = null;
	    		if(MIMEextension.size()==0){
	    			b = BitmapFactory.decodeResource(res, R.drawable.ic_folder);
	    		}else{
	    			
	    			for(FileExtension f:MIMEextension){
	    				for(String s:f.extension){
	    					if(s.equalsIgnoreCase(ext)){
	    						b = f.iconimg;
	    						break;
	    					}
	    				}
	    				if(b!=null){
	    					break;
	    				}
	    			}
	    			if(b==null){
	    				b = BitmapFactory.decodeResource(res, R.drawable.ic_folder);
	    				//icon.setIcon(b);
	    			}
	    			//icon.setIcon(b);
	    			
	    		}
//	    		icon.setIcon(b);
	    		String sizeS = "";
	    		if((file.length()/1000d/1000d/1000d)>1000){//TB
		    		sizeS = String.format(res.getString(R.string.str_tbyte), (file.length()/1024d/1024d/1024d/1024d));
		    	}else if((file.length()/1000d/1000d)>1000){//GB
		    		sizeS = String.format(res.getString(R.string.str_gbyte), (file.length()/1024d/1024d/1024d));
		    	}else if((file.length()/1000d)>1000){//MB
		    		sizeS = String.format(res.getString(R.string.str_mbyte), (file.length()/1024d/1024d));
		    	}else if(file.length()>1000){//KB
		    		sizeS = String.format(res.getString(R.string.str_kbyte), (file.length()/1024d));
		    	}else{//Byte
		    		sizeS = String.format(res.getString(R.string.str_byte), file.length());
		    	}
		    	tvSize.setText(String.format(res.getString(R.string.str_sizecount), sizeS));
	    	}
	    	return convertView;
	   }
	}

	
	
}
