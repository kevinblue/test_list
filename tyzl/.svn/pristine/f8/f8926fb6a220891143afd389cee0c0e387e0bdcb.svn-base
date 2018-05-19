/*--------------------------------------------------|

| dTree 2.05 | www.destroydrop.com/javascript/tree/ |

|---------------------------------------------------|

| Copyright (c) 2002-2003 Geir Landr�               |

|                                                   |

| This script can be used freely as long as all     |

| copyright messages are intact.                    |

|                                                   |

| Updated: 17.04.2003                               |

|--------------------------------------------------*/



// Node object
function Node(id, pid, name, url,icon,iconClose,iconOpen,description,position,open,attributes) {
	this.id = id; //id
	this.pid = pid;//父id
	this.name = name;//节点显示名称
	this.url = url;//单击节点时候a标签的链接地址
	this.icon = icon; //图标
	this.iconClose = iconClose; //图标
	this.iconOpen = iconOpen;//打开时图标
	this.description = description;
	this.position = position;
	this.attributes = attributes||{};
	this.open = open;
	this._io = open || false; //是否被打开
	this._is = false; //是否被选中
	this._ls = false; //是否是父节点的最后一个子节点
	this._hc = false; //是否存在子节点
	this._ai = 0;//被添加的索引
	this._p; //父节点
};
//触发contextMenu事件
function dTreeContextMenuEvent(dtree)
{
	var allNodesArr = dtree.aNodes;
	for(var i=0;i<allNodesArr.length;i++)
	{
		var node = allNodesArr[i];
		var nodeId = i;
		
		if(node.pid != dtree.root.id)
		{
			var contextMenuEventFunc = (function(nodeId,dtree){
				 return function(e){
						try
						{
							getEvent(e).returnValue=false;
							getEvent(e).preventDefault();
							getEvent(e).stopPropagation();
						}catch(e)
						{
							getEvent(e).returnValue=false;
						}
					    dtree.oncontextmenu(getEvent(e),nodeId); 
				 };
			})(nodeId,dtree);
			document.getElementById("s"+dtree.obj+nodeId).oncontextmenu = contextMenuEventFunc;
		}
		else 
		{
			var contextMenuEventFunc = (function(nodeId,dtree){
				 return function(e){
						try
						{
							getEvent(e).returnValue=false;
							getEvent(e).preventDefault();
							getEvent(e).stopPropagation();
						}catch(e)
						{
							getEvent(e).returnValue=false;
						}
					    dtree.onrootcontextmenu(getEvent(e),nodeId); 
				 };
			})(nodeId,dtree);
			document.getElementById("s"+dtree.obj+nodeId).oncontextmenu = contextMenuEventFunc;
		}
	}
}
// Tree object
function dTree(objName) {
	this.config = {
		target		   : null, //a标签的target
		folderLinks	   : true, //关联图标点击
		useSelection   : true, //选中时候高亮
		useCookies	   : true, //使用coolie记录选择的节点
		useLines	   : true, //使用线条进行连接
		useIcons	   : true, //使用图标
		useStatusText  : false, //状态栏显示鼠标所在的节点
		closeSameLevel : false, //关联同一级的所有节点进行关闭操作 
		inOrder		   : false  //父节点在子节点之前进行添加
	};

	this.icon = {
		root		: 'base.gif',
		folder		: 'folder.gif',
		folderOpen	: 'folderopen.gif',
		node		: 'page.gif',
		empty		: getRootPath()+'/images/empty.gif',
		line		: getRootPath()+'/images/line.gif',
		join		: getRootPath()+'/images/join.gif',
		joinBottom	: getRootPath()+'/images/joinbottom.gif',
		plus		: getRootPath()+'/images/plus.gif',
		plusBottom	: getRootPath()+'/images/plusbottom.gif',
		minus		: getRootPath()+'/images/minus.gif',
		minusBottom	: getRootPath()+'/images/minusbottom.gif',
		nlPlus		: getRootPath()+'/images/nolines_plus.gif',
		nlMinus		: getRootPath()+'/images/nolines_minus.gif'
	};
	this.obj = objName;
	this.aNodes = []; //装载所有节点的数组
	this.aIndent = []; //记录缩进量的数组
	this.root = new Node(-1);//根节点为-1
	this.selectedNode = null; //被选择的节点
	this.selectedFound = false;//找到被选择的节点
	this.completed = false;//是否遍历加载完成标志

};
// Adds a new node to the node array
dTree.prototype.add = function(id, pid, name, url,icon,iconClose,iconOpen,description,position,open,attributes) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url,icon,iconClose,iconOpen,description,position,open,attributes);
};

// Open/close all nodes
dTree.prototype.openAll = function() {
	this.oAll(true);
};
dTree.prototype.closeAll = function() {
	this.oAll(false);
};

// Outputs the tree to the page
dTree.prototype.toString = function() {
	var str = '<div class="dtree">\n';
	if (document.getElementById) {
		if (this.config.useCookies) this.selectedNode = this.getSelected();
		str += this.addNode(this.root);
	} else str += 'Browser not supported.';
	str += '</div>';
	if (!this.selectedFound) this.selectedNode = null;
	this.completed = true;
	return str;
};
// Creates the tree structure
dTree.prototype.addNode = function(pNode) {
	var str = '';
	var n=0;
	if (this.config.inOrder) n = pNode._ai;
	for (n; n<this.aNodes.length; n++) {
		if (this.aNodes[n].pid == pNode.id) {
			var cn = this.aNodes[n];
			cn._p = pNode;
			cn._ai = n;
			this.setCS(cn);
			if (!cn.target && this.config.target) cn.target = this.config.target;
			if (cn._hc && !cn._io && this.config.useCookies) cn._io = this.isOpen(cn.id);
			if (!this.config.folderLinks && cn._hc) cn.url = null;
			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {
					cn._is = true;
					this.selectedNode = n;
					this.selectedFound = true;
			}
			str += this.node(cn, n);
			if (cn._ls) break;
		}
	}
	return str;
};
dTree.prototype.oncontextmenu=function(e,nodeIndex){
};
dTree.prototype.onrootcontextmenu=function(e,nodeIndex){
};
dTree.prototype.onrootclick=function(e,nodeIndex){
};
// Creates the node icon, url and text
//nodeId indicator the dtree aNodes[index] index指的是添加的索引从  [ 0 -- (aNodes.length-1) ]
dTree.prototype.node = function(node, nodeId) {
	var str = '<div class="dTreeNode">' + this.indent(node, nodeId);
	if (this.config.useIcons) {
		if(!node.icon) node.icon = this.icon.node;//(this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
		if (!node.iconClose)
			node.iconClose = this.icon.folder;
		if (!node.iconOpen) node.iconOpen = this.icon.folderOpen;//(node._hc) ? this.icon.folderOpen : node.icon;
		if (this.root.id == node.pid) {
			node.icon = this.icon.root;
			node.iconClose = this.icon.root;
			node.iconOpen = this.icon.root;
		}
		var prefix_icon = getRootPath()+"/menuIcons/";
		//if('indexMenuTree'==this.obj){alert(prefix_icon+"或追"+node.icon);}
		node.icon  = prefix_icon+node.icon;
		node.iconClose = prefix_icon+node.iconClose;
		node.iconOpen = prefix_icon+node.iconOpen;
		if(node._hc)
		{
			str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.iconClose) + '" alt="" />';
		}
		else
		{
			str += '<img id="i' + this.obj + nodeId + '" src="' + node.icon + '" alt="" />';
		}
	}
	if(node._is)
	{
		this.selectedNode = nodeId;
	}
	if (node.pid != this.root.id)
	{
		str += '<a id="s'+this.obj+nodeId+'" href="javascript:void(0)" oncontextmenu="javascript:'+this.obj+'.oncontextmenu('+nodeId+')" onclick="javascript: ' + this.obj + '.onclick(' + nodeId + ');" class="'+((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node')+'">';
		//str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="javascript: ' + this.obj + '.onclick(' + nodeId + ')"';
	}
	else 
	{
		str += '<a id="s'+this.obj+nodeId+'" href="javascript:void(0)" oncontextmenu="javascript:'+this.obj+'.onrootcontextmenu('+nodeId+')" onclick="javascript: ' + this.obj + '.onrootclick(' + nodeId + ');" class="'+((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node')+'">';
		//str +='<a href="javascript:void(0)" onclick="javascript:'+this.obj+'.onrootclick('+nodeId+')" oncontextmenu="javascript:'+this.obj+'.onrootcontextmenu('+nodeId+')">';
	}
	str += node.name+'</a>';
	str += '</div>';
	if (node._hc) {
		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';
		str += this.addNode(node);
		str += '</div>';
	}
	this.aIndent.pop();
	return str;
};
// Adds the empty and line icons
dTree.prototype.indent = function(node, nodeId) {
	var str = '';
	if (this.root.id != node.pid) {
		for (var n=0; n<this.aIndent.length; n++)
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';
		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
		if (node._hc) {
			str += '<a href="javascript: ' + this.obj + '.dropDown(' + nodeId + ');"><img id="j' + this.obj + nodeId + '" src="';
			if (!this.config.useLines) str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
			else str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );
			str += '" alt="" /></a>';
		} else str += '<img src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';
	}
	return str;
};



// Checks if a node has any children and if it is the last sibling

dTree.prototype.setCS = function(node) {

	var lastId;

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id) node._hc = true;

		if (this.aNodes[n].pid == node.pid) lastId = this.aNodes[n].id;

	}

	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;

	var cn = this.aNodes[id];

	//if (cn._hc && !this.config.folderLinks) return;

	if (this.selectedNode != id) {

		if (this.selectedNode || this.selectedNode==0) {

			eOld = document.getElementById("s" + this.obj + this.selectedNode);
           if(eOld)
			eOld.className = "node";

		}
		eNew = document.getElementById("s" + this.obj + id);
		eNew.className = "nodeSel";
		this.selectedNode = id;
		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);
	}
	//不兼容火狐
	//window.event.returnValue = false;
	return false;
};
// Toggle Open or close
dTree.prototype.onclick = function(id) {
	
	var cn = this.aNodes[id];
	this.nodeStatus(!cn._io, id, cn._ls);
	cn._io = !cn._io;
	if (this.config.closeSameLevel) this.closeLevel(cn);
	if (this.config.useCookies) this.updateCookie();
};
//Toggle Open or close
dTree.prototype.dropDown = function(id) {
	
	var cn = this.aNodes[id];
	this.nodeStatus(!cn._io, id, cn._ls);
	cn._io = !cn._io;
	if (this.config.closeSameLevel) this.closeLevel(cn);
	if (this.config.useCookies) this.updateCookie();
};
// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {
			this.nodeStatus(status, n, this.aNodes[n]._ls);
			this.aNodes[n]._io = status;
		}
	}
	if (this.config.useCookies) this.updateCookie();
};

// Opens the tree to a specific node
dTree.prototype.openTo = function(nId, bSelect, bFirst) {
	if (!bFirst) {
		for (var n=0; n<this.aNodes.length; n++) {
			if (this.aNodes[n].id == nId) {
				nId=n;
				break;
			}
		}
	}
	var cn=this.aNodes[nId];
	if (cn.pid==this.root.id || !cn._p) return;
	cn._io = true;
	cn._is = bSelect;
	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);
	if (this.completed && bSelect) this.s(cn._ai);
	else if (bSelect) this._sn=cn._ai;
	this.openTo(cn._p._ai, false, true);
};

// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

};



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

};



// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {
	eDiv	= document.getElementById('d' + this.obj + id);
	eJoin	= document.getElementById('j' + this.obj + id);
	if (this.config.useIcons) {
		eIcon	= document.getElementById('i' + this.obj + id);
		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].iconClose;
	}
	eJoin.src = (this.config.useLines)?
	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):
	((status)?this.icon.nlMinus:this.icon.nlPlus);
	eDiv.style.display = (status) ? 'block': 'none';
};

// [Cookie] Clears a cookie
dTree.prototype.clearCookie = function() {
	var now = new Date();
	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);
	this.setCookie('co'+this.obj, 'cookieValue', yesterday);
	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);
};
// [Cookie] Sets value in a cookie
dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {
	document.cookie =
		escape(cookieName) + '=' + escape(cookieValue)
		+ (expires ? '; expires=' + expires.toGMTString() : '')
		+ (path ? '; path=' + path : '')
		+ (domain ? '; domain=' + domain : '')
		+ (secure ? '; secure' : '');
};
// [Cookie] Gets a value from a cookie
dTree.prototype.getCookie = function(cookieName) {
	var cookieValue = '';
	var posName = document.cookie.indexOf(escape(cookieName) + '=');
	if (posName != -1) {
		var posValue = posName + (escape(cookieName) + '=').length;
		var endPos = document.cookie.indexOf(';', posValue);
		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));
		else cookieValue = unescape(document.cookie.substring(posValue));
	}
	return (cookieValue);
};

// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};



// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	};

};