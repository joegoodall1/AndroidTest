package com.candyspace.androidtest.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

	@SerializedName("url")
	private String url;
	@SerializedName("title")
	private String title;
	@SerializedName("body")
	private String body;

	@SerializedName("media")
	private List<Media> mediaList;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<Media> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<Media> media) {
		this.mediaList = media;
	}

	static class Media {

		@SerializedName("type")
		private String type;

		@SerializedName("media-metadata")
		private List<MediaMetadata> mediaMetadataList;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<MediaMetadata> getMediaMetadataList() {
			return mediaMetadataList;
		}

		public void setMediaMetadataList(List<MediaMetadata> mediaMetadataList) {
			this.mediaMetadataList = mediaMetadataList;
		}

		static class MediaMetadata {

			@SerializedName("url")
			private String url;
			@SerializedName("format")
			private String format;

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

			public String getFormat() {
				return format;
			}

			public void setFormat(String format) {
				this.format = format;
			}

		}

	}

}
