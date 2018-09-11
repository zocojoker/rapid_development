/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.foresee.fbrp.util.xml;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ximpleware.AutoPilot;
import com.ximpleware.NavException;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;

/**
 * <pre>
 *  vtd 解析xml代理对象。
 * </pre>
 * 
 * @author wangbozheng wangbozheng@foresee.com.cn
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class VtdXmlOperateDelegate {

	/**
	 * loger
	 */
	private static final Log log = LogFactory
			.getLog(VtdXmlOperateDelegate.class);

	/**
	 * vtd vtd解析导航器。
	 */
	private VTDNav vtdGenNav;

	/**
	 * 
	 * @param xml
	 */
	public VtdXmlOperateDelegate(String xml) {
		this.vtdGenNav = VtdXmlOperateUtil.generateVtdGenNav(xml);
	}

	/**
	 * 返回根据xpath表达式选择的xml节点信息。 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
	 * List<VtdSelectResultWrapper>.
	 * 
	 * @param xml
	 *            字符串.
	 * @param xpath
	 *            表达式.
	 * 
	 * @return List<VtdSelectResultWrapper> or String .
	 */
	public Object selectByXpath(String xpath) {
		return VtdXmlOperateUtil.selectByXpath(this.vtdGenNav, xpath);
	}

	/**
	 * 返回根据xpath表达式选择的xml节点信息。 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
	 * List<VtdSelectResultWrapper>.
	 * 
	 * @param xml
	 *            字符串.
	 * @param xpath
	 *            表达式.
	 * @param wrappeTag
	 *            是否包装节点标签。
	 * @return List<VtdSelectResultWrapper> or String .
	 */
	public Object selectByXpath(String xpath, boolean IsWrappeTag) {
		return VtdXmlOperateUtil.selectNodeTextByXpath(this.vtdGenNav, xpath,
				IsWrappeTag);
	}

	/**
	 * 根据xpath表达式，把游标移到对应节点。
	 * 
	 * @param xpath
	 * @return
	 */
	public boolean moveCursorToByXpath(String xpath) {
		return VtdXmlOperateUtil.moveCursorToByXpath(this.vtdGenNav, xpath);
	}

	/**
	 * 获取报文数据
	 * 
	 * @param requestXml
	 *            请求xml报文
	 * @param xpath
	 *            节点路径
	 * @return 报文节点数据
	 */
	public String getNodeDataByXpath(String xpath) {
		try {
			Object object = this.selectByXpath(xpath);
			if (object == null) {
				object = "";
			} else if (!(object instanceof String)) {
				log.error("请求报文错误，错误原因：存在多个"
						+ xpath
						+ "路径，请求报文为："
						+ new String(this.vtdGenNav.getXML().getBytes(),
								"UTF-8"));
				throw new RuntimeException("请求报文错误！");
			}

			return (String) object;

		} catch (RuntimeException e) {
			try {
				log.error("请求报文结构错误，错误报文为："
						+ new String(this.vtdGenNav.getXML().getBytes(),
								"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				log.error("字符编码转换异常：", e1);
				throw new RuntimeException("请求报文错误！");
			}
			throw new RuntimeException("请求报文错误！");
		} catch (UnsupportedEncodingException e) {
			log.error("字符编码转换异常：", e);
			throw new RuntimeException("请求报文错误！");
		}

	}

	/**
	 * 获取当前游标位置。
	 * 
	 * @return
	 */
	public int getCursorPosition() {
		return this.vtdGenNav.getCurrentIndex();
	}

	/**
	 * 游标回滚到指定位置.
	 * 
	 * @param position
	 */
	public void recoverToPosition(int position) {
		VtdXmlOperateUtil.recoverToPosition(this.vtdGenNav, position);
	}

	/**
	 * 获取当前游标所在节点的文本。 （文本包括有子节点文本信息）
	 * 
	 * @return
	 */
	public String getCurrentNodeText() {
		return VtdXmlOperateUtil.getNodeText(this.vtdGenNav, false);
	}

	/**
	 * 获取当前节点属性，
	 * 
	 * @param attribleName
	 * @param keepPosition
	 *            是否游标位置保持不变。
	 * @return
	 */
	public String getCurrentNodeAttrible(String attribleName,
			boolean keepPosition) {
		return VtdXmlOperateUtil.getNodeAttribue(this.vtdGenNav, attribleName,
				keepPosition);
	}

	/**
	 * 
	 * 根据不同方向移动游标。 移动方向: VtdXmlOperateUtil.NEXT_SIBLING：移动到下个节点
	 * VtdXmlOperateUtil.PREV_SIBLING: 移动到前个节点 VtdXmlOperateUtil.PARENT:移动到父节点
	 * VtdXmlOperateUtil.ROOT: 移动根节点 VtdXmlOperateUtil.FIRST_CHILD: 移动第一个子节点
	 * VtdXmlOperateUtil.LAST_CHILD: 移动最后一次子节点 VtdXmlOperateUtil.BROTHER:
	 * 移动到兄弟节点，NEXT_SIBLING 兄弟优先。 VtdXmlOperateUtil.SELF_OR_BROTHER:
	 * 优先查看是否当前位置满足条件，NEXT_SIBLING 兄弟次之。
	 * 
	 * @param direction
	 *            移动方向.
	 * @param element
	 *            节点名称 移动到PARENT，ROOT忽略该参数
	 * @return boolean 移动是否成功。
	 */
	public boolean moveCursorTo(int direction, String element) {
		return VtdXmlOperateUtil.moveCursorTo(this.vtdGenNav, direction,
				element);
	}

	/**
	 * 移动指定节点,并获取节点文本.
	 * 
	 * @param direction
	 * @param element
	 * @return
	 */
	public String moveCursorToAndGetText(int direction, String element) {
		return VtdXmlOperateUtil.moveCursorToAndGetText(this.vtdGenNav,
				direction, element);
	}

	/**
	 * 
	 * <pre>
	 *  Vtd 处理xml工具。
	 * </pre>
	 * 
	 * @author wangbozheng wangbozheng@foresee.com.cn
	 * @version 1.00.00
	 * 
	 *          <pre>
	 * 修改记录
	 *    修改后版本:     修改人：  修改日期:     修改内容:
	 * </pre>
	 */
	public static class VtdXmlOperateUtil {

		/**
		 * log
		 */
		private static final Log log = LogFactory
				.getLog(VtdXmlOperateUtil.class);
		/**
		 * 下个相邻位置。
		 */
		public static final int NEXT_SIBLING = VTDNav.NEXT_SIBLING;
		/**
		 * 上个相邻位置。
		 */
		public static final int PREV_SIBLING = VTDNav.PREV_SIBLING;
		/**
		 * 父节点位置。
		 */
		public static final int PAREN = VTDNav.PARENT;
		/**
		 * 根节点位置。
		 */
		public static final int ROOT = VTDNav.ROOT;
		/**
		 * 第一个子节点。
		 */
		public static final int FIRST_CHILD = VTDNav.FIRST_CHILD;
		/**
		 * 最后一个子节点。
		 */
		public static final int LAST_CHILD = VTDNav.LAST_CHILD;
		/**
		 * 最近兄弟节点，NEXT_SIBLING 兄弟优先。
		 */
		public static final int BROTHER = 6;
		/**
		 * 优先查看是否当前位置满足条件，NEXT_SIBLING 兄弟次之。
		 */
		public static final int SELF_OR_BROTHER = 7;

		/**
		 * 返回根据xpath表达式选择的xml节点信息。 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
		 * List<VtdSelectResultWrapper>.
		 * 
		 * @param xml
		 *            字符串.
		 * @param xpath
		 *            表达式.
		 * @return List<VtdSelectResultWrapper> or String .
		 */
		public static Object selectByXpath(String xml, String xpath) {

			VTDGen tgen = new VTDGen();
			if (xml == null || xml.equals("")) {
				return null;
			}
			byte[] bs;
			try {
				bs = xml.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException("xml encoding is not utf-8！");
			}
			tgen.setDoc(bs);

			return selectByXpath(tgen.getNav(), xpath);
		}

		/**
		 * 返回根据xpath表达式选择的xml节点信息。 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
		 * List<VtdSelectResultWrapper>.
		 * 
		 * @param genNav
		 *            vtdNav 导航对象.
		 * @param xpath
		 *            表达式.
		 * @return List<VtdSelectResultWrapper> or String .
		 */
		public static Object selectByXpath(VTDNav genNav, String xpath) {
			return selectNodeTextByXpath(genNav, xpath, true);
		}

		/**
		 * 返回根据xpath表达式选择的xml节点信息。 如果值查询结果只有一个节点,且节点值不为子节点,返回String类型.其他返回
		 * List<VtdSelectResultWrapper>.
		 * 
		 * @param genNav
		 *            vtdNav 导航对象.
		 * @param xpath
		 *            表达式.
		 * @param wrappeTag
		 *            是否包装节点标签。
		 * @return List<VtdSelectResultWrapper> or String .
		 */
		public static Object selectNodeTextByXpath(VTDNav genNav, String xpath,
				boolean IsWrappeTag) {

			if (genNav == null) {
				return null;
			}

			List<VtdSelectResultWrapper> lst = new java.util.ArrayList<VtdSelectResultWrapper>();
			try {
				// gen.parse(false);
				// VTDNav nav = gen.getNav();
				AutoPilot autoPilot = new AutoPilot(genNav);
				autoPilot.selectXPath(xpath);
				int pos = -1;
				pos = autoPilot.evalXPath();
				while (pos > 0) {
					VtdSelectResultWrapper wrapper = new VtdSelectResultWrapper();
					wrapper.setNodeName(genNav.toString(pos));
					wrapper.setText(getNodeText(genNav, IsWrappeTag));
					if (genNav.getText() < 0) {
						if (genNav.getContentFragment() >= 0) { // 处理xml标签缩写的情况
							wrapper.setXmlNode(true);
						} else {
							wrapper.setText("");
						}
					}
					lst.add(wrapper);
					pos = autoPilot.evalXPath();
				}
			} catch (Exception e) {
				throw new RuntimeException("报文解析异常", e);
			}
			if ((lst.size() == 1) && !lst.get(0).isXmlNode()) {
				return lst.get(0).getText();
			} else if (lst.size() == 0) {
				return null;
			}
			return lst;
		}

		/**
		 * 根据xpath表达式，把游标移到对应节点。
		 * 
		 * @param genNav
		 * @param xpath
		 * @param IsWrappeTag
		 * @return
		 */
		public static boolean moveCursorToByXpath(VTDNav genNav, String xpath) {
			if (genNav == null) {
				return false;
			}
			try {
				AutoPilot autoPilot = new AutoPilot(genNav);
				autoPilot.selectXPath(xpath);
				int pos = -1;
				pos = autoPilot.evalXPath();
				if (pos < 0) {
					return false;
				}
			} catch (Exception e) {
				throw new RuntimeException("报文解析异常", e);
			}
			return true;
		}

		/**
		 * 获取VTDNav当前节点标签的文本值。
		 * 
		 * @param nav
		 *            VTDNav.
		 * @param wrappeTag
		 *            是否包装节点标签。
		 * @return String 。
		 * @throws NavException
		 *             。
		 */
		public static String getNodeText(VTDNav nav, boolean IsWrappeTag) {
			try {
				int pos = nav.getText();
				if (pos < 0) {
					long posInf = -1;
					if (IsWrappeTag) {
						posInf = nav.getElementFragment();
					} else {
						posInf = nav.getContentFragment();
						if (posInf < 0 && nav.getElementFragment() >= 0) { // 处理xml标签缩写的情况
							return "";
						}
					}
					return nav.toRawString((int) posInf, (int) (posInf >> 32));
				} else {
					return nav.toString(pos);
				}
			} catch (Exception e) {
				throw new RuntimeException("报文解析异常", e);
			}
		}

		/**
		 * 获取当前节点属性，
		 * 
		 * @param nav
		 * @param attribleName
		 * @param keepPosition
		 *            是否游标位置保持不变。
		 * @return
		 */
		public static String getNodeAttribue(VTDNav nav, String attribleName,
				boolean keepPosition) {
			int curPos = nav.getCurrentIndex();
			try {
				int pos = nav.getAttrVal(attribleName);
				if (pos > 0) {
					return nav.toString(pos);
				} else {
					return "";
				}
			} catch (Exception e) {
				throw new RuntimeException("报文解析异常", e);
			} finally {
				try {
					if (keepPosition) {
						nav.recoverNode(curPos);
					}
				} catch (NavException e) {
				}
			}
		}

		/**
		 * 获取报文数据
		 * 
		 * @param requestXml
		 *            请求xml报文
		 * @param xpath
		 *            节点路径
		 * @return 报文节点数据
		 */
		public static String getNodeDataByXpath(String requestXml, String xpath) {
			try {
				Object object = selectByXpath(requestXml, xpath);
				if (object == null) {
					object = "";
				} else if (!(object instanceof String)) {
					log.error("请求报文错误，错误原因：存在多个" + xpath + "路径，请求报文为："
							+ requestXml);
					throw new RuntimeException("请求报文错误！");
				}
				return (String) object;
			} catch (RuntimeException e) {
				log.error("请求报文结构错误，错误报文为：" + requestXml);
				throw new RuntimeException("请求报文错误！");
			}

		}

		/**
		 * 根据不同方向移动游标。 移动方向: VtdXmlOperateUtil.NEXT_SIBLING：移动到下个节点
		 * VtdXmlOperateUtil.PREV_SIBLING: 移动到前个节点
		 * VtdXmlOperateUtil.PARENT:移动到父节点 VtdXmlOperateUtil.ROOT: 移动根节点
		 * VtdXmlOperateUtil.FIRST_CHILD: 移动第一个子节点 VtdXmlOperateUtil.LAST_CHILD:
		 * 移动最后一次子节点 VtdXmlOperateUtil.BROTHER: 兄弟节点，NEXT_SIBLING 兄弟优化。
		 * VtdXmlOperateUtil.SELF_OR_BROTHER 优先查看是否当前位置满足条件，NEXT_SIBLING 兄弟次之。
		 * 
		 * @param nav
		 * @param direction
		 *            移动方向
		 * @param element
		 *            节点名称 移动到PARENT，ROOT忽略该参数
		 * 
		 * @return boolean 移动是否成功。
		 */
		public static boolean moveCursorTo(VTDNav nav, int direction,
				String element) {
			try {
				if (direction == BROTHER || direction == SELF_OR_BROTHER) {
					if (direction == SELF_OR_BROTHER
							&& nav.toString(nav.getCurrentIndex()).equals(
									element)) {
						return true;
					}
					boolean flag = nav.toElement(NEXT_SIBLING, element);
					if (!flag) {
						flag = nav.toElement(PREV_SIBLING, element);
					}
					return flag;
				} else {
					return nav.toElement(direction, element);
				}
			} catch (NavException e) {
				throw new RuntimeException("报文解析异常", e);
			}

		}

		/**
		 * 移动指定节点,并获取节点文本.
		 * 
		 * @param nav
		 * @param direction
		 * @param element
		 * @return
		 */
		public static String moveCursorToAndGetText(VTDNav nav, int direction,
				String element) {
			if (moveCursorTo(nav, direction, element)) {
				return getNodeText(nav, false);
			} else {
				return null;
			}
		}

		/**
		 * 游标回滚到指定位置.
		 * 
		 * @param nav
		 * @param position
		 */
		public static void recoverToPosition(VTDNav nav, int position) {
			try {
				nav.recoverNode(position);
			} catch (NavException e) {
				log.error(e);
				throw new RuntimeException("报文解析异常", e);
			}
		}

		/**
		 * 构造VTDNav对象。
		 * 
		 * @param xml
		 */
		public static VTDNav generateVtdGenNav(String xml) {
			VTDGen gen = new VTDGen();
			if (xml == null || xml.equals("")) {
				return null;
			}
			byte[] bs;
			try {
				bs = xml.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				throw new RuntimeException("xml encoding is not utf-8！");
			}
			gen.setDoc(bs);
			try {
				gen.parse(false);
			} catch (Exception e) {
				throw new RuntimeException("报文解析异常", e);
			}
			return gen.getNav();
		}
	}

}
