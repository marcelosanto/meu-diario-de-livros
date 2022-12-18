package com.example.livrosqueli.screens.main

//@OptIn(ExperimentalAnimationApi::class)
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun ReaderMainScreen(mainViewModel: ReaderMainViewModel, context: Context) {
//
//    val searchWidgetState by mainViewModel.searchWidgetState
//    val searchTextState by mainViewModel.searchTextState
//
//    val lol = remember {
//        mutableStateOf(false)
//    }
//
//    val itemsSelected = remember {
//        mutableStateOf(0)
//    }
//    val anim = remember {
//        mutableStateOf(0)
//    }
//
//    var items by remember {
//        mutableStateOf(
//            (1..20).map {
//                ListItem(
//                    title = "Item $it",
//                    isSelected = false
//                )
//            }
//        )
//    }
//
//    Scaffold(
//        modifier = Modifier.padding(16.dp),
//        topBar = {
//            MainAppBar(
//                searchWidgetState = searchWidgetState,
//                searchTextState = searchTextState,
//                onTextChange = {
//                    mainViewModel.updateSearchTextState(newValue = it)
//                },
//                onCloseClicked = {
//                    mainViewModel.updateSearchTextState(newValue = "")
//                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
//                    lol.value = false
//                },
//                onSearchClicked = {
//                    Log.d("TAG", "Searched Text: $it")
//                },
//
//                onSearchTriggered = {
//                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
//                },
//                selectedItems = itemsSelected.value.toInt(),
//                title = { LoopReverseAnimation(resId = R.raw.loader, bool = anim.value) }
//            )
//        }
//    ) {
//        Column {
//            Button(onClick = {
//                lol.value = !lol.value
//                if (lol.value) mainViewModel.updateSearchWidgetState(SearchWidgetState.TESTED)
//                else mainViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED)
//
//                anim.value = if (anim.value == 0) 1 else if (anim.value == 1) 2 else 1
//
//            }) {
//                Text(text = "Change AppBar")
//            }
//            Text(text = "selecionados: ${itemsSelected.value.toString()}")
//
//            Row() {
//                LoopReverseAnimation(resId = R.raw.loader, bool = anim.value)
//
//                AnimatedContent(
//                    targetState = itemsSelected.value,
//                    transitionSpec = {
//                        if (targetState > initialState) {
//                            slideInVertically { height -> height } + fadeIn() with
//                                    slideOutVertically { height -> -height } + fadeOut()
//                        } else {
//                            slideInVertically { height -> -height } + fadeIn() with
//                                    slideOutVertically { height -> height } + fadeOut()
//                        }.using(
//                            SizeTransform(clip = false)
//                        )
//                    }
//                ) { targetCount ->
//                    Text(text = "$targetCount")
//                }
//            }
//
//
//            LazyColumn(
//                state = rememberLazyListState(),
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                items(items.size) { i ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//
//                                items = items.mapIndexed { j, item ->
//                                    if (i == j) {
//                                        if (!item.isSelected) {
//                                            itemsSelected.value += 1
//                                        } else itemsSelected.value -= 1
//
//                                        item.copy(isSelected = !item.isSelected)
//                                    } else item
//                                }
//                            }
//                            .padding(16.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = items[i].title)
//                        if (items[i].isSelected) {
//                            Icon(
//                                imageVector = Icons.Default.Check,
//                                contentDescription = "Selected",
//                                tint = Color.Green,
//                                modifier = Modifier.size(20.dp)
//                            )
//
//                        }
//                    }
//                }
//            }
//
//
//        }
//    }
//}
//
//@Composable
//fun MainAppBar(
//    searchWidgetState: SearchWidgetState,
//    searchTextState: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit,
//    onSearchTriggered: () -> Unit,
//    selectedItems: Int,
//    title: @Composable () -> Unit
//) {
//    when (searchWidgetState) {
//        SearchWidgetState.CLOSED -> {
//            DefaultAppBar(onSearchClicked = onSearchTriggered, title = title)
//
//        }
//
//        SearchWidgetState.OPENED -> {
//            SearchAppBar(
//                text = searchTextState,
//                onTextChange = onTextChange,
//                onCloseClicked = onCloseClicked,
//                onSearchClicked = onSearchClicked
//            )
//        }
//
//        SearchWidgetState.TESTED -> {
//            CustomAppBar(onCloseClicked = onCloseClicked, selectedItems, isVisible = true)
//        }
//    }
//}
//
//@Composable
//fun DefaultAppBar(onSearchClicked: () -> Unit, title: @Composable () -> Unit) {
//    TopAppBar(
//        title = title,
//        //{
////            Image(
////                painter = painterResource(id = R.drawable.avatar),
////                contentDescription = "avatar",
////                contentScale = ContentScale.Crop,            // crop the image if it's not a square
////                modifier = Modifier
////                    .size(48.dp)
////                    .clip(CircleShape)                       // clip to the circle shape
////                    .border(2.dp, Color.Gray, CircleShape)
////            )   // add a border (optional))
//        //},
//        actions = {
//            IconButton(onClick = { onSearchClicked() }) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//        }
//    )
//}
//
//@Composable
//fun SearchAppBar(
//    text: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit
//) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        //.padding(start = 32.dp),
//        elevation = AppBarDefaults.TopAppBarElevation,
//        color = MaterialTheme.colors.primary
//    ) {
//        TextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = text,
//            onValueChange = {
//                onTextChange(it)
//            },
//            placeholder = {
//                Text(
//                    modifier = Modifier.alpha(ContentAlpha.medium),
//                    text = "Search here...",
//                    color = Color.White
//                )
//            },
//            textStyle = TextStyle(
//                fontSize = MaterialTheme.typography.subtitle1.fontSize
//            ),
//            singleLine = true,
//            leadingIcon = {
//                IconButton(
//                    modifier = Modifier.alpha(ContentAlpha.medium),
//                    onClick = { /*TODO*/ }) {
//
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        if (text.isNotEmpty()) {
//                            onTextChange("")
//                        } else {
//                            onCloseClicked()
//                        }
//                    }) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Close Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(
//                onSearch = { onSearchClicked(text) }
//            ),
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
//                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
//            )
//
//        )
//
//    }
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun CustomAppBar(onCloseClicked: () -> Unit, selectedItems: Int, isVisible: Boolean) {
//    TopAppBar(
//        title = {
//            IconButton(onClick = { onCloseClicked() }) {
//                Icon(
//                    imageVector = Icons.Filled.Close,
//                    contentDescription = "Close Icon",
//                    tint = Color.White
//                )
//            }
//
//            AnimatedContent(
//                targetState = selectedItems,
//                transitionSpec = {
//                    if (targetState > initialState) {
//                        slideInVertically { height -> height } + fadeIn() with
//                                slideOutVertically { height -> -height } + fadeOut()
//                    } else {
//                        slideInVertically { height -> -height } + fadeIn() with
//                                slideOutVertically { height -> height } + fadeOut()
//                    }.using(
//                        SizeTransform(clip = false)
//                    )
//                }
//            ) { targetCount ->
//                Text(text = "$targetCount")
//            }
//
//        },
//        actions = {
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Filled.Pin,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Filled.SurroundSound,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Filled.RestoreFromTrash,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//
//            IconButton(onClick = { }) {
//                Icon(
//                    imageVector = Icons.Filled.PictureAsPdf,
//                    contentDescription = "Search Icon",
//                    tint = Color.White
//                )
//            }
//        }
//    )
//}
//
//@Composable
//@Preview
//fun CustomAppBarPreview() {
//    CustomAppBar({}, selectedItems = 0, isVisible = false)
//}
//
//@Composable
//@Preview
//fun SearchAppBarPreview() {
//    SearchAppBar(
//        text = "Some random text",
//        onTextChange = {},
//        onCloseClicked = { },
//        onSearchClicked = {}
//    )
//}
//
//@Composable
//fun LoopReverseAnimation(
//    modifier: Modifier = Modifier,
//    @RawRes resId: Int,
//    alignment: Alignment = Alignment.Center,
//    enableMergePaths: Boolean = true,
//    bool: Int
//) {
//    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId))
//    val shouldReverse = remember { mutableStateOf(false) }
//    val anim = rememberLottieAnimatable()
//    if (bool == 1)
//        LaunchedEffect(composition) {
//            anim.animate(
//                composition = composition,
//                speed = 1f,
//                clipSpec = LottieClipSpec.Frame(max = 74)
//            )
//            shouldReverse.value = true
//        }
//    if (bool == 2) {
//        LaunchedEffect(composition) {
//            anim.animate(
//                composition = composition,
//                speed = -1f
//            )
//            shouldReverse.value = false
//        }
//    }
//
//    LottieAnimation(
//        composition,
//        anim.value,
//        modifier = modifier,
//        enableMergePaths = remember { enableMergePaths },
//        alignment = alignment
//    )
//}