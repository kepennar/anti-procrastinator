package org.kepennar.aproc.thymeleaf.components

import java.lang.invoke.MethodHandleImpl.BindCaller.T

import org.springframework.data.domain.Page

class PageWrapper<T> {

	class PageItem {
		int number
		boolean current
	}

	public static final int MAX_PAGE_ITEM_DISPLAY = 5
	Page<T> page
	List<PageItem> items = []
	int number
	String url
	String sortFieldName

	PageWrapper(Page<T> page, String url, String sortFieldName) {
		this.page = page
		this.url = url
		this.sortFieldName = sortFieldName
		number = page.getNumber()

		int start, size
		if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
			start = 1
			size = page.getTotalPages()
		} else {
			if (number <= MAX_PAGE_ITEM_DISPLAY / 2) {
				start = 1
				size = MAX_PAGE_ITEM_DISPLAY
			} else if (number >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
				start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY + 1
				size = MAX_PAGE_ITEM_DISPLAY
			} else {
				start = number - MAX_PAGE_ITEM_DISPLAY / 2
				size = MAX_PAGE_ITEM_DISPLAY
			}
		}

		for (int i = 0; i<size; i++) {
			def pageNumber = start+i
			def pageItem = new PageItem(number: pageNumber, current: (pageNumber-1 == number))
			items.add(pageItem)
		}
	}

	List<T> getContent(){
		return page.getContent()
	}

	int getSize(){
		return page.getSize()
	}

	int getTotalPages(){
		return page.getTotalPages()
	}

	boolean isFirstPage(){
		return page.isFirst()
	}

	boolean isLastPage(){
		return page.isLast()
	}

	boolean isHasPreviousPage(){
		return page.hasPrevious()
	}

	boolean isHasNextPage(){
		return page.hasNext()
	}
}
