package com.codewithdj.vrapverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class versePage_Activity extends AppCompatActivity {

    public TextView quotesText;
    public ImageView next;
    public Button more;
    public ImageView prev;
    public Button webVerse;
    public ArrayList<Quote> quoteArrayList;
    public int index;
    public Stack<Quote> prevQuote;
    public boolean isPrevious;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse_page_);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();


        quotesText = findViewById(R.id.textView4);
        next = findViewById(R.id.imageView5);
        prev = findViewById(R.id.imageView6);
        quoteArrayList = new ArrayList<>();
        webVerse = findViewById(R.id.textView5);
        more = findViewById(R.id.textView2);

        //import quotes from string.xml
        Resources resources = getResources();
        String[] all_quotes = resources.getStringArray(R.array.quotes);


        addToQuoteList(all_quotes);



        final int quotesLength = quoteArrayList.size();
        index = getRandomQuote(quotesLength - 1);
        quotesText.setText(quoteArrayList.get(index).toString());
        prevQuote = new Stack<>();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPrevious = false;
                index = getRandomQuote(quotesLength - 1);
                quotesText.setText(quoteArrayList.get(index).toString());
                prevQuote.push(quoteArrayList.get(index));
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPrevious && prevQuote.size() > 0)
                {
                    prevQuote.pop();
                    isPrevious = true;
                }
                if (prevQuote.size() > 0 && isPrevious)
                {
                    quotesText.setText(prevQuote.pop().toString());
                }
                else
                {
                    Toast.makeText(versePage_Activity.this, "Get new Quote", Toast.LENGTH_SHORT).show();
                }
            }
        });

        webVerse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.vrapverse.in/"));
                startActivity(intent);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_TEXT , quoteArrayList.get(index).toString());
                share.setType("text/plain");
                startActivity(share);
            }
        });

    }



    private int getRandomQuote(int length) {
        return (int) (Math.random() * length) + 1;
    }

    private void addToQuoteList(String[] all_quotes) {
        for (int i = 0; i < all_quotes.length; i++)
        {
            String st = all_quotes[i];
            Quote newquote = new Quote(st);
            quoteArrayList.add(newquote);
        }
    }
}