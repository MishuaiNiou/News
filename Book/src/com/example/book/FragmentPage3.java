package com.example.book;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class FragmentPage3 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_3, container,false);		
		
	}	
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		
		
		ExpandableListAdapter adapter = new BaseExpandableListAdapter()
		{
			
			private int[] logos = new int[] { R.drawable.java, R.drawable.ios,
					R.drawable.html5};
			
			int[][] head = new int[][]
			{
				{R.drawable.mr,R.drawable.zz,R.drawable.xy,R.drawable.kkx},
				{R.drawable.mr,R.drawable.xy,R.drawable.kkx},
				{R.drawable.mr,R.drawable.kkx},
				
			};
 			private String[] armTypes = new String[]
				{ "�ҵĺ���", "İ����", "������"};
			private String[][] arms = new String[][]
			{
				{ "����", "����", "Сӣ", "������" },
				{ "����", "Сӣ", "������"  },
				{ "����","������"  }
			};
			@Override
			public Object getChild(int groupPosition, int childPosition)
			{
				return arms[groupPosition][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition)
			{
				return childPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition)
			{
				return arms[groupPosition].length;
			}

			private TextView getTextView()
			{
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);
				TextView textView = new TextView(getActivity());
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			// �÷�������ÿ����ѡ������
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent)
			{
				LinearLayout ll = new LinearLayout(getActivity());
				ll.setOrientation(0);
				ImageView logo = new ImageView(getActivity());
				logo.setImageResource(head[groupPosition][childPosition]);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getChild(groupPosition, childPosition)
						.toString());
				textView.setTextSize(30);
				ll.addView(textView);
				return ll;
			}

			// ��ȡָ����λ�ô���������
			@Override
			public Object getGroup(int groupPosition)
			{
				return armTypes[groupPosition];
			}

			@Override
			public int getGroupCount()
			{
				return armTypes.length;
			}

			@Override
			public long getGroupId(int groupPosition)
			{
				return groupPosition;
			}

			// �÷�������ÿ����ѡ������
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent)
			{
				
				LinearLayout ll = new LinearLayout(getActivity());
				ll.setOrientation(0);
				ImageView logo = new ImageView(getActivity());
				logo.setImageResource(logos[groupPosition]);
				ll.addView(logo);
				TextView textView = getTextView();
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				return ll;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition)
			{
				return true;
			}

			@Override
			public boolean hasStableIds()
			{
				return true;
			}
		};
		ExpandableListView expandListView = (ExpandableListView)getActivity(). findViewById(R.id.expandableListView);
		expandListView.setAdapter(adapter);
			
	
		}
	}



