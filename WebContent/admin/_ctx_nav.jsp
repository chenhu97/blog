<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="vernav2 iconmenu">
	<ul>

		<li><a href="member_list.jsp" class="elements">会员管理</a></li>
		<li><a href="newsCat_list.jsp" class="elements">新闻类别</a></li>
		<li><a href="news_list.jsp" class="elements">新闻管理</a></li>		
		<li><a href="blogger_list.jsp" class="elements">博主申请管理</a></li>
		<li><a href="counter_show.jsp" class="elements">计数器显示</a></li>

	</ul>
	<a class="togglemenu"></a> <br /> <br />
</div>
<!--leftmenu-->

<script>
	jQuery(document)
			.ready(
					function() {

						jQuery('.togglemenu')
								.click(
										function() {
											if (!jQuery(this).hasClass(
													'togglemenu_collapsed')) {

												//if(jQuery('.iconmenu').hasClass('vernav')) {
												if (jQuery('.vernav').length > 0) {
													if (jQuery('.vernav')
															.hasClass(
																	'iconmenu')) {
														jQuery('body')
																.addClass(
																		'withmenucoll');
														jQuery('.iconmenu')
																.addClass(
																		'menucoll');
													} else {
														jQuery('body')
																.addClass(
																		'withmenucoll');
														jQuery('.vernav')
																.addClass(
																		'menucoll')
																.find('ul')
																.hide();
													}
												} else if (jQuery('.vernav2').length > 0) {
													//} else {
													jQuery('body').addClass(
															'withmenucoll2');
													jQuery('.iconmenu')
															.addClass(
																	'menucoll2');
												}

												jQuery(this).addClass(
														'togglemenu_collapsed');

												jQuery(
														'.iconmenu > ul > li > a')
														.each(
																function() {
																	var label = jQuery(
																			this)
																			.text();
																	jQuery(
																			'<li><span>'
																					+ label
																					+ '</span></li>')
																			.insertBefore(
																					jQuery(
																							this)
																							.parent()
																							.find(
																									'ul li:first-child'));
																});
											} else {

												//if(jQuery('.iconmenu').hasClass('vernav')) {
												if (jQuery('.vernav').length > 0) {
													if (jQuery('.vernav')
															.hasClass(
																	'iconmenu')) {
														jQuery('body')
																.removeClass(
																		'withmenucoll');
														jQuery('.iconmenu')
																.removeClass(
																		'menucoll');
													} else {
														jQuery('body')
																.removeClass(
																		'withmenucoll');
														jQuery('.vernav')
																.removeClass(
																		'menucoll')
																.find('ul')
																.show();
													}
												} else if (jQuery('.vernav2').length > 0) {
													//} else {
													jQuery('body').removeClass(
															'withmenucoll2');
													jQuery('.iconmenu')
															.removeClass(
																	'menucoll2');
												}
												jQuery(this).removeClass(
														'togglemenu_collapsed');

												jQuery(
														'.iconmenu ul ul li:first-child')
														.remove();
											}
										});

						jQuery('.userinfo').click(function() {
							if (!jQuery(this).hasClass('active')) {
								jQuery('.userinfodrop').show();
								jQuery(this).addClass('active');
							} else {
								jQuery('.userinfodrop').hide();
								jQuery(this).removeClass('active');
							}
							//remove notification box if visible
							jQuery('.notification').removeClass('active');
							jQuery('.noticontent').remove();

							return false;
						});
						jQuery('.notification a')
								.click(
										function() {
											var t = jQuery(this);
											var url = t.attr('href');
											if (!jQuery('.noticontent').is(
													':visible')) {
												jQuery
														.post(
																url,
																function(data) {
																	t
																			.parent()
																			.append(
																					'<div class="noticontent">'
																							+ data
																							+ '</div>');
																});
												//this will hide user info drop down when visible
												jQuery('.userinfo')
														.removeClass('active');
												jQuery('.userinfodrop').hide();
											} else {
												t.parent()
														.removeClass('active');
												jQuery('.noticontent').hide();
											}
											return false;
										});

						jQuery(document).click(
								function(event) {
									var ud = jQuery('.userinfodrop');
									var nb = jQuery('.noticontent');

									//hide user drop menu when clicked outside of this element
									if (!jQuery(event.target).is(
											'.userinfodrop')
											&& !jQuery(event.target).is(
													'.userdata')
											&& ud.is(':visible')) {
										ud.hide();
										jQuery('.userinfo').removeClass(
												'active');
									}

									//hide notification box when clicked outside of this element
									if (!jQuery(event.target)
											.is('.noticontent')
											&& nb.is(':visible')) {
										nb.remove();
										jQuery('.notification').removeClass(
												'active');
									}
								});
						jQuery('.notitab a').live('click', function() {
							var id = jQuery(this).attr('href');
							jQuery('.notitab li').removeClass('current'); //reset current 
							jQuery(this).parent().addClass('current');
							if (id == '#messages')
								jQuery('#activities').hide();
							else
								jQuery('#messages').hide();

							jQuery(id).show();
							return false;
						});

						jQuery('.vernav > ul li a, .vernav2 > ul li a')
								.each(
										function() {
											var url = jQuery(this).attr('href');
											jQuery(this)
													.click(
															function() {
																if (jQuery(url).length > 0) {
																	if (jQuery(
																			url)
																			.is(
																					':visible')) {
																		if (!jQuery(
																				this)
																				.parents(
																						'div')
																				.hasClass(
																						'menucoll')
																				&& !jQuery(
																						this)
																						.parents(
																								'div')
																						.hasClass(
																								'menucoll2'))
																			jQuery(
																					url)
																					.slideUp();
																	} else {
																		jQuery(
																				'.vernav ul ul, .vernav2 ul ul')
																				.each(
																						function() {
																							jQuery(
																									this)
																									.slideUp();
																						});
																		if (!jQuery(
																				this)
																				.parents(
																						'div')
																				.hasClass(
																						'menucoll')
																				&& !jQuery(
																						this)
																						.parents(
																								'div')
																						.hasClass(
																								'menucoll2'))
																			jQuery(
																					url)
																					.slideDown();
																	}
																	return false;
																}
															});
										});
					});
</script>