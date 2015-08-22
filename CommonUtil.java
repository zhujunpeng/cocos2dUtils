package com.example.pvz.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.view.MotionEvent;

/**
 * ͨ�ù���
 * 
 * @author Administrator
 * 
 */
public class CommonUtil {

	/**
	 * ������Ϸ��ͼ
	 * @param tmxFile
	 * @return
	 */
	public static CCTMXTiledMap loadMap(String tmxFile)
	{
		CCTMXTiledMap gameMap = CCTMXTiledMap.tiledMap(tmxFile);
		
		//�����ֶ�ƽ�Ƶ�ͼ
		gameMap.setAnchorPoint(0.5f, 0.5f);
		CGSize contentSize = gameMap.getContentSize();
		gameMap.setPosition(contentSize.width / 2, contentSize.height / 2);
		
		return gameMap;
	}
	/**
	 * �ӵ�ͼ�м���ָ�����Ƶĵ㼯��
	 * @param map
	 * @param name
	 * @return
	 */
	public static List<CGPoint> loadPoint(CCTMXTiledMap map,String name)
	{
		CCTMXObjectGroup objectGroup = map.objectGroupNamed(name);
		// ��ȡ��ʬλ����Ϣ
		ArrayList<HashMap<String, String>> objects = objectGroup.objects;
		// �ֱ���x��yΪ������ȡ����ֵ��Ϣ---->��װ���㼯����
		List<CGPoint> points = new ArrayList<CGPoint>();
		for (HashMap<String, String> item : objects) {
			float x = Float.parseFloat(item.get("x"));
			float y = Float.parseFloat(item.get("y"));
			points.add(CGPoint.ccp(x, y));
		}
		return points;
	}
	
	
	
	/**
	 * ����֡����(����ֹͣ)
	 * 
	 * @param frames
	 * @param num
	 *            ��ǰ���ص�ͼƬ����
	 * @param filepath
	 *            ·����ͨ�ã�
	 * @return
	 */
	public static CCAction getRepeatForeverAnimate(ArrayList<CCSpriteFrame> frames, int num, String filepath) {
		if (frames == null) {
			frames = new ArrayList<CCSpriteFrame>();
			for (int i = 1; i <= num; i++) {
				frames.add(CCSprite.sprite(String.format(filepath, i)).displayedFrame());
			}
		}
		CCAnimation anim = CCAnimation.animation("", 0.2f, frames);

		CCAnimate animate = CCAnimate.action(anim);
		return CCRepeatForever.action(animate);
	}

	/**
	 * ����һ�ε�����֡
	 */
	public static CCAnimate getAnimate(ArrayList<CCSpriteFrame> frames, int num, String filepath) {
		if (frames == null) {
			frames = new ArrayList<CCSpriteFrame>();
			// frames��Ϣ����
			for (int i = 1; i <= num; i++) {
				frames.add(CCSprite.sprite(String.format(filepath, i)).displayedFrame());
			}
		}
		CCAnimation animation = CCAnimation.animation("", 0.2f, frames);
		return CCAnimate.action(animation, false);// ֻ����һ��
	}

	/**
	 * �ж��Ƿ񱻵��
	 * 
	 * @param event
	 * @param node
	 * @return
	 */
	public static boolean isClicke(MotionEvent event, CCLayer layer, CCNode node) {
		CGPoint point = layer.convertTouchToNodeSpace(event);
		return CGRect.containsPoint(node.getBoundingBox(), point);
	}

	//
	// /**
	// * �ж��Ƿ񱻵��
	// *
	// * @param touchPoint
	// * @param node
	// * @return
	// */
	// public static boolean isClicke(CGPoint touchPoint, CCNode node) {
	// return CGRect.containsPoint(node.getBoundingBox(), touchPoint);
	// }
	/**
	 * �л�����
	 * @param targetLayer
	 */
	public static void changeLayer(CCLayer targetLayer)
	{
		CCScene scene = CCScene.node();
		scene.addChild(targetLayer);
		CCFadeTransition transition = CCFadeTransition.transition(1, scene);
		CCDirector.sharedDirector().replaceScene(transition);
	}

}
