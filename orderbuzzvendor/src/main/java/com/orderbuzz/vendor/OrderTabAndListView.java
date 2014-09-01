package com.orderbuzz.vendor;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class OrderTabAndListView extends TabActivity {
	// TabSpec Names
	private static final String INBOX_SPEC = "Pending Orders";
	private static final String OUTBOX_SPEC = "Done Orders";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost);
    
        Bundle data = getIntent().getExtras();
		final String restId  = data.getString("restId");
	
        TabHost tabHost = getTabHost();
        // Inbox Tab
        TabSpec inboxSpec = tabHost.newTabSpec(INBOX_SPEC);
        // Tab Icon
        inboxSpec.setIndicator(INBOX_SPEC, getResources().getDrawable(R.drawable.icon_inbox));
        Intent inboxIntent = new Intent(this, PendingOrderListViewActivity.class);
        Bundle bundle = new Bundle();
		bundle.putString("restId", restId);
		inboxIntent.putExtras(bundle);

        // Tab Content
        inboxSpec.setContent(inboxIntent);
        
        // Outbox Tab
        TabSpec outboxSpec = tabHost.newTabSpec(OUTBOX_SPEC);
        outboxSpec.setIndicator(OUTBOX_SPEC, getResources().getDrawable(R.drawable.icon_outbox));
        Intent outboxIntent = new Intent(this, DoneOrderListViewActivity.class);
        bundle = new Bundle();
		bundle.putString("restId", restId);
		outboxIntent.putExtras(bundle);

        outboxSpec.setContent(outboxIntent);
        
        // Adding all TabSpec to TabHost
        tabHost.addTab(inboxSpec); // Adding Inbox tab
        tabHost.addTab(outboxSpec); // Adding Outbox tab
        
    }
}