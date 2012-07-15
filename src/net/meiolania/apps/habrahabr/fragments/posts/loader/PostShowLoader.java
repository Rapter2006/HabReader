package net.meiolania.apps.habrahabr.fragments.posts.loader;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import net.meiolania.apps.habrahabr.data.PostsFullData;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class PostShowLoader extends AsyncTaskLoader<PostsFullData>{
	private String url;

	public PostShowLoader(Context context, String url){
		super(context);
		this.url = url;
	}

	@Override
	public PostsFullData loadInBackground(){
		PostsFullData data = new PostsFullData();

		try{
			Document document = Jsoup.connect(url).get();
			Element title = document.select("span.post_title").first();
			Element hubs = document.select("div.hubs").first();
			Element content = document.select("div.content").first();
			Element date = document.select("div.published").first();
			Element author = document.select("div.author > a").first();

			data.setUrl(url);
			data.setTitle(title.text());
			data.setHubs(hubs.text());
			data.setContent(content.html());
			data.setDate(date.text());
			data.setAuthor(author.text());
		}
		catch(IOException e){
		}

		return data;
	}

}