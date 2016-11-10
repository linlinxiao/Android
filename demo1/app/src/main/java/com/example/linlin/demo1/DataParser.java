package com.example.linlin.demo1;

import com.hishop.mobile.app.HiApplication;
import com.hishop.mobile.entities.AdVo;
import com.hishop.mobile.entities.AppHomeTopic;
import com.hishop.mobile.entities.AppInformationVo;
import com.hishop.mobile.entities.BalanceVo;
import com.hishop.mobile.entities.CategoryVo;
import com.hishop.mobile.entities.CertVo;
import com.hishop.mobile.entities.CouponVo;
import com.hishop.mobile.entities.FavoriteVo;
import com.hishop.mobile.entities.GroupAttributeValueVo;
import com.hishop.mobile.entities.GroupDetailVo;
import com.hishop.mobile.entities.GroupItemVo;
import com.hishop.mobile.entities.GroupMemberVo;
import com.hishop.mobile.entities.GroupProductList;
import com.hishop.mobile.entities.GroupShareVo;
import com.hishop.mobile.entities.GroupSkuItemVo;
import com.hishop.mobile.entities.GroupSkus;
import com.hishop.mobile.entities.HomeTopicVo;
import com.hishop.mobile.entities.ImprestVo;
import com.hishop.mobile.entities.ListResultVo;
import com.hishop.mobile.entities.LotteryVo;
import com.hishop.mobile.entities.MemberListVo;
import com.hishop.mobile.entities.MyGroupDetailVo;
import com.hishop.mobile.entities.MyGroupListVo;
import com.hishop.mobile.entities.MyGroupMemberListVo;
import com.hishop.mobile.entities.NavigateVo;
import com.hishop.mobile.entities.OperationResultVo;
import com.hishop.mobile.entities.OrderVo;
import com.hishop.mobile.entities.PointVo;
import com.hishop.mobile.entities.ProductDetailVo;
import com.hishop.mobile.entities.ProductSkuVo;
import com.hishop.mobile.entities.ProductStandardValueVo;
import com.hishop.mobile.entities.ProductStandardVo;
import com.hishop.mobile.entities.ProductVo;
import com.hishop.mobile.entities.RegisterOptVo;
import com.hishop.mobile.entities.SignInVo;
import com.hishop.mobile.entities.UserInfoExtraVo;
import com.hishop.mobile.entities.UserInfoVo;
import com.hishop.mobile.exceptions.HiDataException;
import com.hishop.mobile.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataParser {
	
	public static AppInformationVo getAppInformation(String doc) throws Exception
	{
		AppInformationVo app = new AppInformationVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		
		app.Version = json.getJSONObject("Result").getString("version").trim();
		app.AppUrl = json.getJSONObject("Result").getString("upgradeUrl").trim();
		app.Description= json.getJSONObject("Result").getString("description").trim();
		app.Forcible = json.getJSONObject("Result").getBoolean("forcible");
		app.splashImage = json.getJSONObject("Result").optString("StartImg").trim();
		return app;
	}

	public static boolean isSupportMQService(String doc) throws Exception {
		boolean isSupportMQService;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}

		isSupportMQService = json.getJSONObject("Result").optBoolean("IsOpenMeiQiaService",false);


		return isSupportMQService;
	}
	
	public static List<AdVo> getHomeAds(String doc) throws Exception
	{
		List<AdVo> ads = new ArrayList<AdVo>();
		AdVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("advs");
		for(int i=0;i<datas.length();i++)
		{			
			item = new AdVo();
			item.Picture = datas.getJSONObject(i).getString("pic");
			item.Url = datas.getJSONObject(i).getString("url");
			item.Name = datas.getJSONObject(i).getString("description");
			ads.add(item);
		}
//		if (json.getInt("Code") != 0)
//		{
//			HiDataException error = new HiDataException();
//			error.ErrorCode = json.getInt("Code");
//			error.Message = json.getString("ErrMsg");
//			throw error;
//		}
		return ads;
	}
	
	public static List<NavigateVo> getHomeNavigate(String doc) throws Exception
	{
		List<NavigateVo> ads = new ArrayList<NavigateVo>();
		NavigateVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("navigates");
		for(int i=0;i<datas.length();i++)
		{			
			item = new NavigateVo();
			item.Picture = datas.getJSONObject(i).getString("pic");
			item.Url = datas.getJSONObject(i).getString("url");
			item.Name = datas.getJSONObject(i).getString("description");
			ads.add(item);
		}
		return ads;
	}
	
	public static SignInVo getHomeSignIn(String doc) throws Exception
	{
		SignInVo ret = new SignInVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject object = json.getJSONObject("Result");
		ret.Status = Integer.parseInt(object.getString("status"));
		ret.Points = Integer.parseInt(object.getString("points"));
		ret.Integral = Integer.parseInt(object.getString("integral"));
			
		return ret;
	}	
	
	/**
	 * 授奖接口
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static LotteryVo getLotteryResult(String doc) throws Exception
	{
		LotteryVo ret = new LotteryVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject object = json.getJSONObject("Result");
		ret.Type = Integer.parseInt(object.getString("type"));
		ret.Name = object.optString("name");
		ret.Content = object.getString("content");
		ret.Integral = object.getString("integral");
		ret.Times = object.getString("times");
		
		return ret;
	}	
			
		
	public static List<HomeTopicVo> getHomeTopic(String doc) throws Exception
	{
		List<HomeTopicVo> topics = new ArrayList<HomeTopicVo>();
		HomeTopicVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("topics");
		for(int i=0;i<datas.length();i++)
		{			
			item = new HomeTopicVo();
			item.Id = datas.getJSONObject(i).getString("tid");
			item.Picture = datas.getJSONObject(i).getString("pic");
			item.Url = datas.getJSONObject(i).getString("url");
			item.Name = datas.getJSONObject(i).getString("title");
			topics.add(item);
		}
		return topics;
	}

	public static List<AppHomeTopic> getAppHomeTopic(String doc) throws Exception
	{
		String jsonStr = convertStandardJSONString(doc);
		List<AppHomeTopic> topics = new ArrayList<AppHomeTopic>();
		AppHomeTopic item;
		JSONTokener parser = new JSONTokener(jsonStr);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONArray("LModules");
		for(int i=0;i<datas.length();i++)
		{
			JSONObject contentObj = datas.getJSONObject(i).getJSONObject("content");
			item = new AppHomeTopic();
			item.type = datas.getJSONObject(i).getInt("type");

			JSONArray urlArr = contentObj.getJSONArray("dataset");
			List<AppHomeTopic.UrlModel> urls = new ArrayList< AppHomeTopic.UrlModel>();
			for (int j = 0; j<urlArr.length(); j++) {
				String baseUrl = new HiApplication().getAppConfig().RestfulServer;
				String pic = urlArr.getJSONObject(j).getString("pic");
				AppHomeTopic.UrlModel url = item.new UrlModel();
				url.link = urlArr.getJSONObject(j).optString("link");
				url.pic = pic.contains("http") ? pic : baseUrl + pic;
				urls.add(url);
			}

			item.dataset = urls;
			topics.add(item);
		}
		return topics;
	}

	public static String convertStandardJSONString(String data_json) {
		data_json = data_json.replaceAll("\\\\r\\\\n", "");
		data_json = data_json.replace("\"{", "{");
		data_json = data_json.replace("}\",", "},");
		data_json = data_json.replace("}\"", "}");
		return data_json;
	}

	public static String getHomeTopicVersion(String doc) throws Exception {
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		return json.getJSONObject("Result").getString("HomeTopicVersion");
	}

	public static String getHomeTopicPath(String doc) throws Exception {
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		return json.getJSONObject("Result").getString("HomeTopicPath");
	}
	
	public static List<ProductVo> getHomeItems(String doc) throws Exception
	{
		List<ProductVo> Result = new ArrayList<ProductVo>();
		ProductVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("tagProducts");
		for(int i=0;i<datas.length();i++)
		{			
			item = new ProductVo();
			item.Id = datas.getJSONObject(i).getString("pid");
			item.Name = datas.getJSONObject(i).getString("name");
			item.Picture = datas.getJSONObject(i).getString("pic");
			item.Price = datas.getJSONObject(i).getString("price");
			item.Url = datas.getJSONObject(i).getString("url");
			item.SaleCount= datas.getJSONObject(i).getString("saleCounts");
			Result.add(item);
		}
//		if (json.getInt("Code") != 0)
//		{
//			HiDataException error = new HiDataException();
//			error.ErrorCode = json.getInt("Code");
//			error.Message = json.getString("ErrMsg");
//			throw error;
//		}
		return Result;
		
	}
	
	public static List<CategoryVo> getCategories(String doc) throws Exception
	{
		List<CategoryVo> Result = new ArrayList<CategoryVo>();
		CategoryVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("Subs");
		
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new CategoryVo();
			item.Name = o.getString("name");
			item.Cid = o.getString("cid");
			item.Picture = o.optString("icon");
			item.HasChildren = o.getBoolean("hasChildren");
			item.Desc = o.optString("description");
			item.subCategories = "{\"Subs\":"+o.getString("Subs")+"}";
			Result.add(item);
		}
		
		return Result;
	}
	
	public static List<CategoryVo> getSubCategories(String doc) throws Exception
	{
		List<CategoryVo> Result = new ArrayList<CategoryVo>();
		CategoryVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("code");
			error.Message = err.getString("msg");
			throw error;
		}
		JSONArray datas = json.getJSONArray("Subs");
		
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new CategoryVo();
			item.Name = o.getString("name");
			item.Cid = o.getString("cid"); 
			item.HasChildren = o.optBoolean("hasChildren");
			item.Desc = o.optString("description");
			item.subCategories = "{\"Subs\":"+o.optString("Subs")+"}";
			Result.add(item);
		}
		
		return Result;
	}



	public static GroupShareVo getGroupShareDetial(String doc) throws Exception {
		GroupShareVo Result = new GroupShareVo();

		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null) {
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}

		JSONObject result = json.getJSONObject("Result");
		Result.Name = result.getString("ShareTitle");
		Result.Desc = result.getString("ShareContent");
		Result.ShareUrl = result.getString("ShareLink");
		Result.Image = result.getString("ShareImage");

		return Result;
	}


	public static ProductDetailVo getProductDetail(String doc) throws Exception {
		List<String> AllSelect = new ArrayList<String>();//可以选择所有规格
		ProductDetailVo Result =new ProductDetailVo();
		
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject result = json.getJSONObject("Result");
		Result.ActivityUrl = result.getString("ActivityUrl").trim();
//		Result.IsOpenSale = true;
//		Result.IsOpenSale = result.getString("IsOpenSale").toLowerCase().equals("true");
//		Result.IsOpenSale = result.optString("IsOpenSale").toLowerCase().equals("true");
		Result.Name = result.getString("ProductName");
		Result.SaleCount = result.getString("SaleCounts");
		Result.ShowSaleCounts = result.getString("ShowSaleCounts");
		Result.Freight = result.getDouble("Freight");
		Result.HasStore = result.getBoolean("HasStores");
		Result.IsSupportPodrequest = result.getBoolean("IsSupportPodrequest");
		Result.MarketPrice = result.getString("MarketPrice");
		Result.Price = result.getString("MinSalePrice");
		Result.MinSalePrice = result.getString("MinSalePrice");
		Result.MaxSalePrice = result.getString("MaxSalePrice");
		Result.Stock = result.getString("Stock");
		Result.IsFavorite = result.getString("IsFavorite").toLowerCase().equals("true");
		Result.Desc = result.getString("ShortDescription");
		Result.DefaultSKUId = result.getJSONObject("DefaultSku").getString("SkuId");
		Result.Id = Result.DefaultSKUId.split("_")[0];
		Result.PromotionContent = result.getString("OrderPromotionInfo");
		Result.GiftContent = result.getString("ProductSendGiftsInfo");
		Result.CouponContent = result.getString("Coupons");
		Result.ReviewCount = result.getString("ReviewCount");
		Result.ConsultationCount = result.getString("ConsultationCount");
		Result.ProductReduce = result.getString("ProductReduce");
		Result.MobileExclusive = result.optDouble("MobileExclusive",0);
		Result.IsUnSale = result.optBoolean("IsUnSale",false);
		Result.FightGroupActivityId = result.optInt("FightGroupActivityId",0);
		Result.GradeName = result.optString("GradeName");
		JSONObject promotionObj = result.optJSONObject("Promotion");
		if (promotionObj != null)
		{
			Result.HasPromotion = true;
			Result.PromotionType = promotionObj.getString("promotiontype");
			Result.PromotionTitle = promotionObj.getString("promotionname");
		}
		String t = result.getString("ImageUrl1").trim();
		if(!StringUtil.isNull(t) && !t.equalsIgnoreCase("null"))
			Result.Images.add(t);
		t = result.getString("ImageUrl2").trim();
		if(!StringUtil.isNull(t) && !t.equalsIgnoreCase("null"))
			Result.Images.add(t);
		t = result.getString("ImageUrl3").trim();
		if(!StringUtil.isNull(t) && !t.equalsIgnoreCase("null"))
			Result.Images.add(t);
		t = result.getString("ImageUrl4").trim();
		if(!StringUtil.isNull(t) && !t.equalsIgnoreCase("null"))
			Result.Images.add(t);
		t = result.getString("ImageUrl5").trim();
		if(!StringUtil.isNull(t) && !t.equalsIgnoreCase("null"))
			Result.Images.add(t);
		
		JSONArray standards = result.getJSONArray("SkuItem");
		for(int i=0;i<standards.length();i++)
		{
			JSONObject o = standards.getJSONObject(i);
			ProductStandardVo item = new ProductStandardVo();
			item.Id = o.getString("AttributeId");
			item.Name = o.getString("AttributeName");
			JSONArray values = o.getJSONArray("AttributeValue");
			for(int j=0;j<values.length();j++)
			{
				ProductStandardValueVo v = new ProductStandardValueVo();
				JSONObject m = values.getJSONObject(j);
				v.Id = m.getString("ValueId");
				v.Name = m.getString("Value");
				v.hasStock = true;
				v.hasImage = m.getString("UseAttributeImage").equalsIgnoreCase("True");
				v.ImageUrl = m.getString("ImageUrl");
				item.Values.add(v);
			}
			Result.Standards.add(item);
		}

		/**
		 * 这里是获取所有的规格笛卡尔积
		 */
		int skuItemsSize = Result.Standards.size();
		StringBuilder sb = new StringBuilder();
		sb.append(Result.Id+"==");
		for(int i = 0;i<skuItemsSize;i++){
			if(i != 0){
				sb.append("==");
			}
			int arrtibuteValueSize = Result.Standards.get(i).Values.size();
			for(int j = 0;j<arrtibuteValueSize;j++){
				sb.append(Result.Standards.get(i).Values.get(j).Id);
				if(j != arrtibuteValueSize-1){
					sb.append(",");
				}
			}

		}
		AllSelect = StringUtil.descartes(sb.toString());


		JSONArray SKUS = result.getJSONArray("Skus");
		List<String> skusIdList = new ArrayList<String>();
		for(int i=0;i<SKUS.length();i++) {
			JSONObject o = SKUS.getJSONObject(i);
			ProductSkuVo item = new ProductSkuVo();
			item.Id = o.getString("SkuId");
			item.Stock = Integer.parseInt(o.getString("Stock").trim());
			item.SalePrice = String.format("%.2f",o.getDouble("SalePrice"));//o.getString("SalePrice");
			skusIdList.add(item.Id);
			Result.SKUs.add(item);			
		}

		for(int i = 0;i<AllSelect.size();i++){
			if(!skusIdList.contains(AllSelect.get(i))){
				ProductSkuVo bean = new ProductSkuVo();
				bean.Id = AllSelect.get(i);
				bean.Stock = 0;
				bean.SalePrice = "0";//o.getString("SalePrice");
				Result.SKUs.add(bean);
			}
		}
		
		return Result;
	}

	public static List<ProductVo> getProductRecommendeds(String doc) throws Exception
	{
		List<ProductVo> Result =new ArrayList<>();

		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject result = json.getJSONObject("Result");
		JSONArray ps = result.getJSONArray("GuessYouLikeProducts");
		for(int i=0;i<ps.length();i++)
		{
			JSONObject o = ps.getJSONObject(i);
			ProductVo item = new ProductVo();
			item.Id = o.getString("ProductId");
			item.Name = o.getString("ProductName");
			item.Picture = o.getString("ThumbnailUrl180");
			item.Price = String.format("¥%.2f",o.getDouble("SalePrice"));
			Result.add(item);
		}

		return Result;
	}

	public static String getPoint(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Result").getString("TotalPoints");
	}

	/**
	 * 返回开启预付款结果
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static boolean getAdvanceOpenResult(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Success").getString("Status").equalsIgnoreCase("true");
	}

	/**
	 * 返回取消订单结果
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static boolean getCancelOrderResult(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Success").getString("Status").equalsIgnoreCase("true");
	}

	/**
	 * 返回删除收藏结果
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static boolean getDeleteFavoriteResult(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Success").getString("Status").equalsIgnoreCase("true");
	}

	/***
	 * 订单列表
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static  ListResultVo<OrderVo> getOrderList(String doc) throws Exception
	{
		ListResultVo<OrderVo> Result =new ListResultVo<OrderVo>();


		OrderVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		int count = datas.length();
		for(int i=0;i<count;i++)
		{
			JSONObject o = datas.getJSONObject(i);
			JSONArray products = o.getJSONArray("LineItems");
			JSONArray gifts = o.getJSONArray("Gifts");

			int len = products.length();
			for(int j=0;j<len;j++) {
				JSONObject p = products.getJSONObject(j);
				item = new OrderVo();
				item.OrderId = o.getString("OrderId");
				item.Status = o.getInt("Status");
				item.StatusText = o.getString("StatusText");
				item.Quantity = o.getInt("Quantity");
				item.Amount = o.getDouble("Amount");
				item.SupplierId = o.optInt("SupplierId");
				item.ShipperName = o.optString("ShipperName");

				item.ProductName = p.getString("Name");
				item.ProductId = p.getString("Id");
				item.ProductPrice = p.getDouble("Price");
				item.ProductAmount = p.getInt("Amount");
				item.ProductSkuText = p.getString("SkuText");
				item.ProductImage = p.getString("Image");
				item.productStatueText = p.optString("StatusText","");

				item.CanCloseOrder = o.getBoolean("IsShowClose");
				item.CanFinishOrder = o.getBoolean("IsShowFinishOrder");
				item.CanPreviewOrder = o.getBoolean("IsShowPreview");//查看评论
				item.IsShowCreview = o.optBoolean("IsShowCreview",false);//true 评价订单
				item.CanRefundOrder = o.getBoolean("IsShowRefund");
				item.CanReturnOrder = o.getBoolean("IsShowReturn");
				item.CanShowOrderTakeCode = o.getBoolean("IsShowTakeCodeQRCode");
				item.CanShowLogistics = o.getBoolean("IsShowLogistics");

				if (j==0)
					item.HasHeader = true;
				if (j == len-1)
					item.HasFooter = true;
				Result.Data.add(item);
			}

			if (products.length() != 0) continue;
			int allGiftQuantity = 0;
			for (int j = 0; j < gifts.length(); j++) {
				JSONObject p = gifts.getJSONObject(j);
				item = new OrderVo();
				item.OrderId = o.getString("OrderId");
				item.Status = o.getInt("Status");
				item.StatusText = o.getString("StatusText");
				item.Quantity = o.getInt("Quantity");
				item.Amount = o.getDouble("Amount");
				item.SupplierId = o.optInt("SupplierId");
				item.ShipperName = o.optString("ShipperName");

				item.isGift = true;
				item.GiftId = p.getInt("GiftId");
				item.GiftName = p.getString("GiftName");
				item.PromoteType = p.getInt("PromoteType");
				item.GiftQuantity = p.getInt("Quantity");
				item.SkuId = p.optString("SkuId");
				item.Image = p.optString("Image");
				item.CostPrice = p.getDouble("CostPrice");
				allGiftQuantity += item.GiftQuantity;

				item.CanCloseOrder = o.getBoolean("IsShowClose");
				item.CanFinishOrder = o.getBoolean("IsShowFinishOrder");
				item.CanPreviewOrder = o.getBoolean("IsShowPreview");//查看评论
				item.IsShowCreview = o.optBoolean("IsShowCreview",false);//true 评价订单
				item.CanRefundOrder = o.getBoolean("IsShowRefund");
				item.CanReturnOrder = o.getBoolean("IsShowReturn");
				item.CanShowOrderTakeCode = o.getBoolean("IsShowTakeCodeQRCode");
				item.CanShowLogistics = o.getBoolean("IsShowLogistics");

				if (j==0)
					item.HasHeader = true;
				if (j == gifts.length()-1){
					item.HasFooter = true;
					item.AllGiftQuantity = allGiftQuantity;
				}
				Result.Data.add(item);
			}
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordCount"));
		Result.CurrentCount = count;

		return Result;
	}

	/***
	 * 订单列表
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static  ListResultVo<FavoriteVo> getFavoriteList(String doc) throws Exception
	{
		ListResultVo<FavoriteVo> Result =new ListResultVo<FavoriteVo>();


		FavoriteVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		int count = datas.length();
		for(int i=0;i<count;i++)
		{
			JSONObject o = datas.getJSONObject(i);
				item = new FavoriteVo();
				item.ProductId = o.getString("ProductId");
				item.ProductName = o.getString("ProductName");
				item.ProductImage = o.getString("ThumbnailUrl100");
//				item.MarketPrice = o.getDouble("MarketPrice");
				item.RankPrice = o.getDouble("SalePrice");
				item.FavoriteId = o.getString("FavoriteId");
				Result.Data.add(item);
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordCount"));
		Result.CurrentCount = count;

		return Result;
	}

	public static  ListResultVo<ImprestVo> getImprestList(String doc) throws Exception
	{
		ListResultVo<ImprestVo> Result =new ListResultVo<ImprestVo>();


		ImprestVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new ImprestVo();
			item.Type = o.getInt("TradeType");
			item.TypeName = o.getString("TradeTypeText");
			item.HappendAt = o.getString("TradeDate").replace('T',' ');
			item.Money = o.getDouble("Amount");
			Result.Data.add(item);
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordCount"));
		Result.CurrentCount = Result.Data.size();

		return Result;
	}

	public static  ListResultVo<PointVo> getPoints(String doc) throws Exception
	{
		ListResultVo<PointVo> Result =new ListResultVo<PointVo>();


		PointVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new PointVo();
			item.Name = o.getString("TradeTypeName");
			item.HappendAt = o.getString("TradeDate");
			item.Mark = o.getString("Remark");
			item.Point = Integer.parseInt(o.getString("Point"));
			Result.Data.add(item);
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordCount"));
		Result.CurrentCount = Result.Data.size();

		return Result;
	}
	
	public static ListResultVo<ProductVo> getProducts(String doc) throws Exception
	{
		ListResultVo<ProductVo> Result =new ListResultVo<ProductVo>();
		
		
		ProductVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new ProductVo();
			item.Id = o.getString("pid");
			item.Name = o.getString("name");
			item.Picture = o.getString("pic");
			item.Price = o.getString("price");
			item.Url = o.getString("url");
			item.SaleCount = o.getString("saleCounts");
			Result.Data.add(item);
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordCount"));
		Result.CurrentCount = Result.Data.size();
		
		return Result;
	}		
	public static List<ProductVo> getProducts1(String doc) throws Exception
	{
		List<ProductVo> Result = new ArrayList<ProductVo>();
		ProductVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("code");
			error.Message = err.getString("msg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("trade_get_response").getJSONArray("product");
		
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new ProductVo();
			item.Id = o.getString("Iid");
			item.Name = o.getString("Title");
			item.Picture = o.getString("PicUrl");
			item.Price = o.getString("RankPrice");
			item.Url = o.getString("Url");
			item.SaleCount = o.getString("SaleCounts");
			Result.add(item);
		}
		
		return Result;
	}
	
	public static boolean getProductFavorite(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		
		return json.getJSONObject("Success").getString("Status").toLowerCase().equalsIgnoreCase("true");
	}
	
	public static List<CertVo> getCertProducts(String doc) throws Exception
	{
		List<CertVo> Result = new ArrayList<CertVo>();
		CertVo item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("CartItemInfo");
		ArrayList<CertVo> invalidProduct = new ArrayList<>();
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new CertVo();
			item.HasStore = o.getString("HasStore").equalsIgnoreCase("True");
			item.Stock = Integer.parseInt(o.getString("Stock"));
			item.ProductId = o.getString("ProductId");
			item.SkuId = o.getString("SkuID");
			item.Quantity = Integer.parseInt(o.getString("Quantity"));
			item.Name = o.getString("Name");
			item.SkuContent = o.getString("SkuContent");
			item.Price = o.getString("MemberPrice");
			item.Total = o.getString("SubTotal");
			item.Image = o.getString("ThumbnailUrl100");
			item.IsMobileExclusive = o.optBoolean("IsMobileExclusive",false);
			item.IsValid = o.optBoolean("IsValid",true);
			item.HasEnoughStock = o.optBoolean("HasEnoughStock",true);
			item.SupplierId = o.optInt("SupplierId");
			item.SupplierName = o.optString("SupplierName");
			item.CostPrice = o.optInt("CostPrice");

			if (item.IsValid && item.HasEnoughStock)Result.add(item);
			else invalidProduct.add(item);

		}

		datas = json.getJSONObject("Result").getJSONArray("GiftInfo");
		for(int i=0;i<datas.length();i++)
		{
			JSONObject o = datas.getJSONObject(i);
			item = new CertVo();
			item.Type = 1;
			item.ProductId = o.getString("GiftId");
			item.Quantity = Integer.parseInt(o.getString("Quantity"));
			item.Name = o.getString("Name");
			item.Point = o.getString("NeedPoint");
			item.SubPointTotal = o.getString("SubPointTotal");
			item.Image = o.getString("ThumbnailUrl100");
			item.SupplierId = o.optInt("SupplierId");
			item.SupplierName = o.optString("SupplierName");
			item.CostPrice = o.optInt("CostPrice");
			Result.add(item);
		}
		Result.addAll(invalidProduct);
		
		return Result;
	}	
	
	public static UserInfoVo getUserInfo(String doc) throws Exception
	{
		UserInfoVo Result = new UserInfoVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject datas = json.getJSONObject("Result");
		
		Result.UID = datas.getString("uid");
		Result.GradeId = datas.getString("gradeId");
		Result.SessionToken = datas.getString("sessionid");
		Result.realName = datas.optString("realName",null);
		Result.Cellphone = datas.optString("Cellphone",null);
		Result.Email = datas.optString("Email",null);

		return Result;
	}
	
	public static UserInfoExtraVo getUserInfoExtra(String doc) throws Exception
	{
		UserInfoExtraVo Result = new UserInfoExtraVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject datas = json.getJSONObject("Result");
		
		Result.RealName = datas.getString("realName");
		Result.GradeName = datas.getString("gradeName");
		Result.GradeId = datas.getString("gradeId");
		Result.Points = datas.getString("points");
//		Result.Balance = datas.getString("expenditure");
		Result.WaitFinishCount = datas.getString("waitFinishCount");
		Result.WaitPayCount = datas.getString("waitPayCount");
		Result.couponsCount = datas.getString("couponsCount");
		Result.picture = datas.getString("picture");
		Result.EnableAppShake = datas.getString("EnableAppShake").equalsIgnoreCase("True");
		Result.IsOpenBalance = datas.getString("IsOpenBalance").toLowerCase().equals("true");
		Result.Balance = datas.getString("Balance");
		Result.ReferralStatus = Integer.parseInt(datas.getString("ReferralStatus"));
		Result.FightGroupActiveNumber = datas.optInt("FightGroupActiveNumber",0);
		Result.Email = datas.optString("Email",null);
		Result.Cellphone = datas.optString("Cellphone",null);
		Result.CellPhoneVerification = datas.optBoolean("CellPhoneVerification",false);
		Result.EmailVerification = datas.optBoolean("EmailVerification",false);
		return Result;
	}

	/***
	 * 取得预付款数据
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static BalanceVo getUserBalance(String doc) throws Exception
	{
		BalanceVo Result = new BalanceVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject datas = json.getJSONObject("Result");

		Result.IsOpenBalance = datas.getString("IsOpenBalance").toLowerCase().equals("true");
		Result.Balance = datas.getString("Balance");
		Result.RequestBalance = datas.getString("RequestBalance");
		return Result;
	}

	public static List<CouponVo> getProductCoupons(String doc)
	{
		List<CouponVo> ret = new ArrayList<>();
		JSONTokener parser = new JSONTokener(doc);
		try {
			JSONArray data =  (JSONArray) parser.nextValue();
			for(int i=0;i<data.length();i++)
			{
				JSONObject coupon = data.getJSONObject(i);
				CouponVo item = new CouponVo();
				item.Id = coupon.getInt("CouponId");
				item.Price = coupon.getDouble("Price");
				item.Time = coupon.getString("StartTimeText")+"-"+coupon.getString("ClosingTimeText");
				item.OrderUseLimit = coupon.getDouble("OrderUseLimit");
				item.Condition = coupon.getString("LimitText");
				ret.add(item);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static boolean getTakeCouponResult(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Result").getString("Status").equalsIgnoreCase("SUCCESS");
	}

	public static boolean getOperationResult(String doc) throws Exception
	{
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		return json.getJSONObject("Result").getString("Status").equalsIgnoreCase("True");
	}

	public static OperationResultVo getOperationResult1(String doc) throws Exception
	{
		OperationResultVo ret = new OperationResultVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		ret.Success = json.getJSONObject("Success").getString("Status").equalsIgnoreCase("True");
		ret.Message =json.getJSONObject("Success").getString("Msg");
		return  ret;
	}


	/***
	 * 火拼团列表
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public static  ListResultVo<GroupProductList> getGroupProductList(String doc) throws Exception
	{
		ListResultVo<GroupProductList> Result =new ListResultVo<GroupProductList>();


		GroupProductList item;
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONArray datas = json.getJSONObject("Result").getJSONArray("List");
		int count = datas.length();
		for(int i=0;i<count;i++)
		{
			JSONObject gp = datas.getJSONObject(i);
			item = new GroupProductList();

			item.ActivityId = gp.getString("ActivityId");
			item.ProductId = gp.getString("ProductId");
			item.ProductName = gp.getString("ProductName");
			item.ActivityImage = gp.getString("ActivityImage");
			item.StartTime = gp.getString("StartTime");
			item.EndTime = gp.getString("EndTime");
			item.MaxJoinCount = gp.getString("MaxJoinCount");
			item.LimitedHour = gp.getString("LimitedHour");
			item.MaxCount = gp.getString("MaxCount");
			item.SalePrice = gp.getString("SalePrice");
			item.FightPrice = gp.getString("FightPrice");
			item.Status = gp.getString("Status");
			item.StatusText = gp.getString("StatusText");

			Result.Data.add(item);
		}
		Result.TotalCount = Integer.parseInt(json.getJSONObject("Result").getString("RecordTotal"));
		Result.CurrentCount = count;

		return Result;
	}

	/**
	 * 获评商品详情
	 * @param doc
	 * @return
	 * @throws Exception
     */
	public static GroupDetailVo getGroupProductDetail(String doc) throws Exception {
		List<String> AllSelect = new ArrayList<String>();//可以选择所有规格
		GroupDetailVo groupDetailVo = new GroupDetailVo();

		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}

		JSONObject obj = json.getJSONObject("Result");
		groupDetailVo.ActivityId = obj.getString("ActivityId");//火拼团活动ID
		groupDetailVo.ProductId = obj.getString("ProductId");//商品ID
		groupDetailVo.ProductName = obj.getString("ProductName");//商品名称
		groupDetailVo.ActivityImage = obj.getString("ActivityImage");//活动图片
		groupDetailVo.StartTime = obj.getString("StartTime");//开始时间
		groupDetailVo.EndTime = obj.getString("EndTime");//结束时间
		groupDetailVo.MaxJoinCount = obj.getString("MaxJoinCount");//参团人数
		groupDetailVo.LimitedHour = obj.getString("LimitedHour");//成团时限
		groupDetailVo.MaxCount = obj.getString("MaxCount");//每人限购数量
		groupDetailVo.SalePrice = obj.getString("SalePrice");//一口价
		groupDetailVo.FightPrice = obj.getString("FightPrice");//火拼价
		groupDetailVo.Status = obj.getString("Status");//状态  2 即将开始  1 进行中 3 已结束
		groupDetailVo.StatusText = obj.getString("StatusText");//状态文本   2 即将开始  1 进行中  3 已结束
		groupDetailVo.GroupCounts = obj.getString("GroupCounts");//组团个数
		groupDetailVo.RemainTime = obj.getString("RemainTime");//活动截止剩余时间
		/**
		 * 解析商品详情
		 */
		JSONObject proInfo = obj.getJSONObject("ProductInfo");
		groupDetailVo.productInfo.ProductId = proInfo.getString("ProductId");//商品ID
		groupDetailVo.productInfo.Stock = proInfo.getString("Stock");//商品总库存
		groupDetailVo.productInfo.ActivityStock = proInfo.optString("ActivityStock","0");//商品总库存
		groupDetailVo.productInfo.SalePrice = proInfo.getString("SalePrice");//一口价
		groupDetailVo.productInfo.CostPrice = proInfo.getString("CostPrice");//会员价
		groupDetailVo.productInfo.PromotionInfo = proInfo.getString("PromotionInfo");//促销信息
		groupDetailVo.productInfo.ConsultationCount = proInfo.getString("ConsultationCount");//咨询总数
		groupDetailVo.productInfo.ReviewCount = proInfo.getString("ReviewCount");//评论总数
		groupDetailVo.productInfo.IsSupportPodrequest = proInfo.optBoolean("IsSupportPodrequest",false);//是否支持上门自提  0 不支持  1 支持
		groupDetailVo.productInfo.IsSupportTakeOnStore = proInfo.optBoolean("IsSupportTakeOnStore",false);//是否可以货到付款  0 不支持  1 支持
		groupDetailVo.productInfo.ProductImages = proInfo.getString("ProductImages");//商品图片列表
		groupDetailVo.productInfo.DefaultImage = proInfo.getString("DefaultImage");//默认图片
		for(String img : groupDetailVo.productInfo.ProductImages.split(",")){
			if(!StringUtil.isNull(img)){
				groupDetailVo.productInfo.productpics.add(img);
			}
		}
		if (groupDetailVo.productInfo.productpics.size() == 0){
			if(!StringUtil.isNull(groupDetailVo.productInfo.DefaultImage)){
				groupDetailVo.productInfo.productpics.add(groupDetailVo.productInfo.DefaultImage);
			}
		}

		/**
		 * 解析默认的规格,,此处SkuItems 和 MemberPrices未解析
		 */
		JSONObject defaultObj = proInfo.getJSONObject("DefaultSku");

		groupDetailVo.productInfo.defaultSku.SkuId = defaultObj.optString("SkuId",null);//规格编号
		groupDetailVo.productInfo.defaultSku.ProductId = defaultObj.optString("ProductId",null);//
		groupDetailVo.productInfo.defaultSku.SKU = defaultObj.optString("SKU",null);//规格描述
		groupDetailVo.productInfo.defaultSku.Weight = defaultObj.optString("Weight",null);//重量
		groupDetailVo.productInfo.defaultSku.Stock = defaultObj.optString("Stock",null);//库存
		groupDetailVo.productInfo.defaultSku.WarningStock = defaultObj.optString("WarningStock",null);//警告库存
		groupDetailVo.productInfo.defaultSku.CostPrice = defaultObj.optString("CostPrice",null);//活动价格
		groupDetailVo.productInfo.defaultSku.SalePrice = defaultObj.optString("SalePrice",null);//一口价
		groupDetailVo.productInfo.defaultSku.StoreStock = defaultObj.optString("StoreStock",null);//
		groupDetailVo.productInfo.defaultSku.ImageUrl = defaultObj.optString("ImageUrl",null);//规格图片
		groupDetailVo.productInfo.defaultSku.ThumbnailUrl40 = defaultObj.optString("ThumbnailUrl40",null);//
		groupDetailVo.productInfo.defaultSku.ThumbnailUrl410 = defaultObj.optString("ThumbnailUrl410",null);//
		groupDetailVo.productInfo.defaultSku.MaxStock = defaultObj.optString("MaxStock",null);//
		groupDetailVo.productInfo.defaultSku.FreezeStock = defaultObj.optString("FreezeStock",null);//

		/**
		 * 解析规格属性列表
		 */
		JSONArray jsonArr = proInfo.getJSONArray("SkuItem");
		if(jsonArr != null){
			int len = jsonArr.length();
			for(int i = 0;i<len;i++){
				GroupSkuItemVo item = new GroupSkuItemVo();
				JSONObject data = (JSONObject) jsonArr.get(i);
				item.AttributeId = data.getString("AttributeId");
				item.AttributeName = data.getString("AttributeName");
				/**
				 * 解析属性值
				 */
				JSONArray arr = data.getJSONArray("AttributeValue");
				if(arr != null){
					int count = arr.length();
					for(int j = 0;j<count;j++){
						GroupAttributeValueVo attributeValue = new GroupAttributeValueVo();
						JSONObject object = arr.getJSONObject(j);
						attributeValue.ValueId = object.getString("ValueId");//值Id
						attributeValue.UseAttributeImage = object.getString("UseAttributeImage");//是否图片属性
						attributeValue.Value = object.getString("Value");//值
						attributeValue.ImageUrl = object.getString("ImageUrl");//图片地址
						item.attributeValue.add(attributeValue);
					}
				}
				groupDetailVo.productInfo.skuItems.add(item);
			}
		}

		/**
		 * 这里是获取所有的规格笛卡尔积
		 */
		int skuItemsSize = groupDetailVo.productInfo.skuItems.size();
		StringBuilder sb = new StringBuilder();
		sb.append(groupDetailVo.productInfo.ProductId+"==");
		for(int i = 0;i<skuItemsSize;i++){
			if(i != 0){
				sb.append("==");
			}
			int arrtibuteValueSize = groupDetailVo.productInfo.skuItems.get(i).attributeValue.size();
			for(int j = 0;j<arrtibuteValueSize;j++){
				sb.append(groupDetailVo.productInfo.skuItems.get(i).attributeValue.get(j).ValueId);
				if(j != arrtibuteValueSize-1){
					sb.append(",");
				}
			}

		}
		AllSelect = StringUtil.descartes(sb.toString());

		/**
		 * 解析规格列表
		 */
		JSONArray skusArr = proInfo.getJSONArray("Skus");
		List<String> skusIdList = new ArrayList<String>();
		if(skusArr != null){
			int len = skusArr.length();
			for(int i =0;i<len;i++){
				GroupSkus bean = new GroupSkus();
				JSONObject js = (JSONObject) skusArr.get(i);
				bean.SkuId = js.getString("SkuId");//规格编号
				bean.SKU = js.getString("SKU");//规格描述
				bean.Weight = js.getString("Weight");//重量
				bean.Stock = js.getString("Stock");//库存
				bean.WarningStock = js.getString("WarningStock");//警告库存
				bean.ActivityStock = js.getString("ActivityStock");//活动库存
				bean.BoughtCount = js.getString("BoughtCount");//已售件数
				bean.ActivityPrice = js.getString("ActivityPrice");//活动价格
				bean.SalePrice = js.getString("SalePrice");//一口价
				bean.ImageUrl = js.getString("ImageUrl");//规格图片
				skusIdList.add(bean.SkuId);
				groupDetailVo.productInfo.skus.add(bean);
			}
			for(int i = 0;i<AllSelect.size();i++){
				if(!skusIdList.contains(AllSelect.get(i))){
					GroupSkus bean = new GroupSkus();
					bean.SkuId = AllSelect.get(i);//规格编号
					bean.SKU = "0";//规格描述
					bean.Weight = "0";//重量
					bean.Stock = "0";//库存
					bean.WarningStock = "0";//警告库存
					bean.ActivityStock = "0";//活动库存
					bean.BoughtCount = "0";//已售件数
					bean.ActivityPrice = "0";//活动价格
					bean.SalePrice = "0";//一口价
					bean.ImageUrl = "";//规格图片
					groupDetailVo.productInfo.skus.add(bean);
				}
			}
		}
		/**
		 * 解析组团列表
		 */
		JSONArray groupArr = obj.getJSONArray("GroupItems");
		if(groupArr != null){
			int len = groupArr.length();
			for(int i = 0;i < len;i++){
				GroupItemVo item = new GroupItemVo();
				JSONObject jObj = (JSONObject) groupArr.get(i);
				item.FightGroupId = jObj.getString("FightGroupId");//组团开始时间
				item.StartTime = jObj.getString("StartTime");//组团开始时间
				item.EndTime = jObj.getString("EndTime");//组团结束时间
				item.JoinNumber = jObj.getString("JoinNumber");//参团人数
				item.CreateTime = jObj.getString("CreateTime");//成团时间
				item.Status = jObj.getString("Status");//状态   0 组团中 1组团成功  2 组团失败
				item.StatusText = jObj.getString("StatusText");//状态文本
				item.HeadImage = jObj.getString("HeadImage");//团长头像
				item.NeedJoinNumber = jObj.getString("NeedJoinNumber");//还需要多少人成团
				item.RemainTime = jObj.getInt("RemainTime");//剩余组团时间
				item.NickName = jObj.getString("NickName");//团长昵称
				groupDetailVo.groupItems.add(item);
			}
		}
		return groupDetailVo;
	}

	/**
	 * 火拼团员列表
	 * @param doc
	 * @return
	 * @throws Exception
     */
	public static GroupMemberVo getGroupMember(String doc) throws Exception {
		GroupMemberVo memberVo = new GroupMemberVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}

		JSONObject obj = json.getJSONObject("Result");
		memberVo.GroupId = obj.getString("GroupId");//团ID
		memberVo.ProductId = obj.getString("ProductId");//商品ID
		memberVo.ProductName = obj.getString("ProductName");//商品名称
		memberVo.ProductImage = obj.getString("ProductImage");//商品图片
		memberVo.ActivityImage = obj.getString("ActivityImage");//活动图片
		memberVo.StartTime = obj.getString("StartTime");//成团时间
		memberVo.EndTime = obj.getString("EndTime");//结束时间
		memberVo.CreateTime = obj.getString("CreateTime");//结束时间
		memberVo.MaxJoinCount = obj.getString("MaxJoinCount");//参团人数
		memberVo.LimitedHour = obj.getString("LimitedHour");//成团时限
		memberVo.IsGroupMaster = obj.getBoolean("IsGroupMaster");//会员是否是团长
		memberVo.SalePrice = obj.getString("SalePrice");//一口价
		memberVo.FightPrice = obj.getString("FightPrice");//火拼价
		memberVo.Status = obj.getString("Status");//活动状态  0 未开始  1 进行中  2 已结束
		memberVo.StatusText = obj.getString("StatusText");//活动状态文本
		memberVo.GroupStatus = obj.getString("GroupStatus");//团状态  0 组团中 1组团成功  2 组团失败
		memberVo.GroupStatusText = obj.getString("GroupStatusText");//团状态文本
		memberVo.ActivityId = obj.getString("ActivityId");//火拼团活动ID
		memberVo.RemainTime = obj.getInt("RemainTime");//组团截止剩余时间
		memberVo.ReviewCount = obj.optString("ReviewCount","0");//商品评价总数
		memberVo.ConsultationCount = obj.optString("ConsultationCount","0");//商品咨询总数
		memberVo.UserIsJoinGroup = obj.optBoolean("UserIsJoinGroup",false);//会员是否参团
		memberVo.productPics = Arrays.asList(memberVo.ProductImage.split(","));

		JSONArray arr = obj.getJSONArray("GroupMembers");
		if(arr != null){
			int len = arr.length();
			for(int i = 0;i<len;i++){
				MemberListVo bean = new MemberListVo();
				JSONObject object = arr.getJSONObject(i);
				bean.UserId = object.getString("UserId");//用户ID
				bean.NickName = object.getString("NickName");//用户昵称
				bean.HeadImage = object.getString("HeadImage");//用户头像
				bean.JoinTime = object.optString("JoinTime","000000");//参团时间
				memberVo.GroupMembers.add(bean);
			}
		}
		return  memberVo;
	}

	/**
	 * 我的火拼团列表
	 * @param doc
	 * @return
	 * @throws Exception
     */
	public static MyGroupListVo getMyGroupList(String doc) throws Exception {
		MyGroupListVo listVo = new MyGroupListVo();

		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();

		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}

		JSONObject obj = json.getJSONObject("Result");
		listVo.RecordTotal = obj.getString("RecordTotal");
		JSONArray listArr = obj.getJSONArray("List");
		if(listArr != null){
			int len = listArr.length();
			for(int i = 0;i<len;i++){
				MyGroupDetailVo detailVo = new MyGroupDetailVo();
				JSONObject object = listArr.getJSONObject(i);
				detailVo.GroupId = object.getString("GroupId");//团ID
				detailVo.ProductId = object.getString("ProductId");//商品ID
				detailVo.ProductName = object.getString("ProductName");//商品名称
				detailVo.ProductImage = object.getString("ProductImage");//商品图片
				detailVo.ActivityImage = object.getString("ActivityImage");//活动图片
				detailVo.StartTime = object.getString("StartTime");//成团时间
				detailVo.EndTime = object.getString("EndTime");//结束时间
				detailVo.CreateTime = object.optString("CreateTime",null);
				detailVo.MaxJoinCount = object.getString("MaxJoinCount");//参团人数
				detailVo.LimitedHour = object.getString("LimitedHour");//成团时限
				detailVo.SalePrice = object.getString("SalePrice");//一口价
				detailVo.FightPrice = object.getString("FightPrice");//火拼价
				detailVo.Status = object.getString("Status");//活动状态  2 即将开始  1 进行  3 已结束
				detailVo.IsGroupMaster = object.getString("IsGroupMaster");//会员是否是团长
				detailVo.StatusText = object.getString("StatusText");//活动状态文本
				detailVo.GroupStatus = object.getString("GroupStatus");//团状态
				detailVo.GroupStatusText = object.getString("GroupStatusText");//团状态文本  0 组团中 1组团成功  2 组团失败
				detailVo.ActivityId = object.getString("ActivityId");//火拼团活动ID
				detailVo.RemainTime = object.getInt("RemainTime");//组团截止剩余时间
				detailVo.OrderId = object.optString("OrderId",null);//订单id
				JSONArray jsonArr = object.getJSONArray("GroupMembers");
				if(jsonArr != null){
					int count = jsonArr.length();
					for(int j = 0;j<count;j++){
						MyGroupMemberListVo member = new MyGroupMemberListVo();
						JSONObject js = jsonArr.getJSONObject(j);
						member.UserId = js.getString("UserId");//用户ID
						member.NickName = js.getString("NickName");//用户昵称
						member.HeadImage = js.getString("HeadImage");//用户头像
						member.IsMaster = js.getString("IsMaster");//是否团长
						detailVo.members.add(member);
					}
				}
				listVo.detailList.add(detailVo);
			}
		}

		return listVo;
	}

	public static RegisterOptVo getRegisterOpt(String doc) throws Exception {

		RegisterOptVo item = new RegisterOptVo();
		JSONTokener parser = new JSONTokener(doc);
		JSONObject json = (JSONObject) parser.nextValue();
		JSONObject err = json.optJSONObject("ErrorResponse");
		if (err != null)
		{
			HiDataException error = new HiDataException();
			error.ErrorCode = err.getInt("ErrorCode");
			error.Message = err.getString("ErrorMsg");
			throw error;
		}
		JSONObject datas = json.getJSONObject("Result");

		item.IsOpenMeiQiaService = datas.optString("IsOpenMeiQiaService");
		item.IsSuportPhoneRegister = datas.optString("IsSuportPhoneRegister");
		item.IsSuportEmailRegister = datas.optString("IsSuportEmailRegister");
		item.IsValidEmail = datas.optString("IsValidEmail");
		item.RegisterExtendInfo = datas.optString("RegisterExtendInfo");

		return item;
	}



}
