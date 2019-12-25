package com.module.request.msg;

import com.alibaba.fastjson.annotation.JSONField;

public class MsgBody {
    private Text text;
    private Image image;
    private ShareLink shareLink;
    private RichText richText;
    private Custom custom;
    private Video video;
    private File file;
    private Voice voice;

    public void setVoice(Voice voice) {
        this.voice = voice;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }

    public Custom getCustom() {
        return custom;
    }

    @JSONField(name = "rich_text")
    public void setRichText(RichText richText) {
        this.richText = richText;
    }

    @JSONField(name = "rich_text")
    public RichText getRichText() {
        return richText;
    }

    @JSONField(name = "share_link")
    public void setShareLink(ShareLink shareLink) {
        this.shareLink = shareLink;
    }

    @JSONField(name = "share_link")
    public ShareLink getShareLink() {
        return shareLink;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }


    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

}
