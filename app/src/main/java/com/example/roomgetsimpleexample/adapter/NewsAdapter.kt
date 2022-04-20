package com.example.roomgetsimpleexample.adapterimport android.content.Contextimport android.content.Intentimport android.net.Uriimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport android.widget.ImageViewimport android.widget.TextViewimport androidx.recyclerview.widget.RecyclerViewimport com.bumptech.glide.Glideimport com.example.roomexample.Articleimport com.example.roomgetsimpleexample.Rclass NewsAdapter (    val context: Context,    val articles: List<Article>):RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)        return ArticleViewHolder(view)    }    lateinit var art1:Article    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {        val article = articles[position]        art1 = article        holder.newsTitle.text = article.title        holder.newDescription.text = article.description        Glide.with(context).load(article.urlToImage).into(holder.newsImage)        val newsImage = holder.newsImage        newsImage.setOnClickListener{            val url = art1.url            //val url = "https://google.com"            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {  (it) }            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)            context.startActivity(intent)        }    }    override fun getItemCount(): Int {        return articles.size    }    class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)        var newDescription = itemView.findViewById<TextView>(R.id.newsDescriptions)    }}