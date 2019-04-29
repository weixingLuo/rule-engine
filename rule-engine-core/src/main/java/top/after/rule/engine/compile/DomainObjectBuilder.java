package top.after.rule.engine.compile;

import java.util.Stack;

import top.after.rule.engine.domain.entity.DomainObjectDefine;
import top.after.rule.engine.domain.operate.value.DomainObject;

public class DomainObjectBuilder {
	private static final String SEPARATOR = "的";
	
	private DomainObjectDefine domainRoot;
	
	public DomainObjectBuilder(DomainObjectDefine domainRoot){
		this.domainRoot = domainRoot;
	}
	/**
	 * 基于已有领域对象构建对象实例
	 * @param placeHolder
	 * @return
	 */
	public DomainObject buildDomainObject(String placeHolder){
		return getReference(placeHolder,CompileContext.get().getPath());
	}
	
	/**
	 * 新建领域对象
	 * @param placeHolder
	 * @param objectDefine
	 * @return
	 */
	public DomainObject createDomainObject(String placeHolder,DomainObjectDefine objectDefine){
		String[] atom = getAtomPlaceHolder(placeHolder);
		DomainObject domainObject = new DomainObject(objectDefine,atom);
		domainRoot.getFileds().put(domainObject.getPath()[0],domainObject.getReferenceNode());
		return domainObject;
	}
	
	@SuppressWarnings("unchecked")
	public DomainObject getReference(String placeHolder,String[] context){
		Stack<DomainObjectDefine> stack = getContextStack(context);
		String[] atom = getAtomPlaceHolder(placeHolder);
		String head = atom[0];
		DomainObjectDefine headNode=null;
		Stack<DomainObjectDefine> findStack = (Stack<DomainObjectDefine>)stack.clone();
		while(!findStack.isEmpty()) {
			DomainObjectDefine parant = findStack.pop();
			headNode = findNode(parant,head);
			if(headNode != null) {
				findStack.push(parant);
				findStack.push(headNode);
				break;
			}
		}
		int length = atom.length;
		if(length <2) {
			return buildReference(findStack);
		}
		
		for(int i=1;i<length;i++){
			headNode = findNode(headNode,atom[i]);
			if(headNode==null)
				return null;
			
			findStack.push(headNode);
		}
		return buildReference(findStack);
	}
	/*
	 * private Stack<DomainObjectDefine> findInContext(Stack<DomainObjectDefine>
	 * stack,String[] atom){ DomainObjectDefine currentNode = stack.pop();
	 * Stack<DomainObjectDefine> referenceStack = new Stack<>(); for(int
	 * i=0;i<atom.length;i++){ currentNode = findNode(currentNode,atom[i]);
	 * if(currentNode == null) break; referenceStack.push(currentNode); } boolean
	 * hint = referenceStack.size() == atom.length; if(!hint) return null;
	 * 
	 * while(!stack.isEmpty()){ referenceStack.push(stack.pop()); } return
	 * referenceStack; }
	 */
	
	private DomainObject buildReference(Stack<DomainObjectDefine> referenceStack){
		if(referenceStack.empty()) {
			return null;
		}
		DomainObjectDefine referenceNode = referenceStack.peek();
		referenceStack.remove(0);
		String[] path = referenceStack.stream().map(o->o.getName()).toArray(String[]::new);
		return new DomainObject(referenceNode,path);
	}
	
	private String[] getAtomPlaceHolder(String placeHolder){
		if(placeHolder.contains(SEPARATOR)){
			return placeHolder.split(SEPARATOR);
		}
		return new String[]{placeHolder};
	}
	
	private Stack<DomainObjectDefine> getContextStack(String[] context){
		Stack<DomainObjectDefine> stack =  new Stack<>();
		DomainObjectDefine node = domainRoot;
		stack.add(node);
		int length = context.length;
		if(length <2) {
			return stack;
		}
		
		for(int i=1;i<length;i++){
			node = findNode(node,context[i]);
			if(node!=null)
				stack.push(node);
			else
				break;
		}
		return stack;
	}
	
	private DomainObjectDefine findNode(DomainObjectDefine paranet,String name){
		return paranet.getFileds().get(name);
	}
}
