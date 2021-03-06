
/****** Object:  Database [karmasport]    Script Date: 06/07/2020 4:25:16 CH ******/
CREATE DATABASE [karmasport2]

GO
USE [karmasport2]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](255) NULL,
	[name] [nvarchar](255) NULL,
	[enable] [bit] NOT NULL,
	[password] [varchar](255) NULL,
	[role] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](255) NULL,
	[order_date] [datetime2](7) NULL,
	[phone] [varchar](255) NULL,
	[received_date] [datetime2](7) NULL,
	[total_money] [bigint] NULL,
	[customer_id] [bigint] NULL,
	[description] [text] NULL,
	[status_id] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill_detai]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill_detai](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[price] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[bill_id] [bigint] NULL,
	[productid] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bill_status]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bill_status](
	[id] [int] NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NULL,
	[customer_id] [bigint] NULL,
	[product_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[category_id] [bigint] NOT NULL,
	[category_name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[category_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[customer_id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [nvarchar](200) NULL,
	[birthday] [datetime2](7) NULL,
	[customer_name] [nvarchar](100) NULL,
	[email] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hibernate_sequence]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hibernate_sequence](
	[next_val] [bigint] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 06/07/2020 4:25:16 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[productid] [bigint] NOT NULL,
	[image] [varchar](255) NULL,
	[price] [int] NOT NULL,
	[product_name] [varchar](255) NULL,
	[quantity] [int] NOT NULL,
	[status] [bit] NOT NULL,
	[category_id] [bigint] NULL,
	[description] [text] NULL,
	[image1] [varchar](255) NULL,
	[image2] [varchar](255) NULL,
	[sale] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[admin] ON 

INSERT [dbo].[admin] ([id], [email], [name], [enable], [password], [role]) VALUES (1, N'admin@gmail.com', N'admin', 1, N'12345678', 0)
INSERT [dbo].[admin] ([id], [email], [name], [enable], [password], [role]) VALUES (2, N'dainvph09220@gmail.com', N'Nguyễn Văn Đại', 1, N'12345678', 1)
SET IDENTITY_INSERT [dbo].[admin] OFF
SET IDENTITY_INSERT [dbo].[bill] ON 

INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (8, N'Việt Nam             ', CAST(N'2020-06-21T22:38:48.1750000' AS DateTime2), N'0916089813', CAST(N'2020-06-27T22:30:20.3690000' AS DateTime2), 4423, 10003, N'

', 4)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (9, N'Việt Nam             ', CAST(N'2020-06-21T22:56:54.1760000' AS DateTime2), N'0916089813', NULL, 1914, 10003, N'
', 2)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (12, N'Việt Nam', CAST(N'2020-06-24T00:44:51.9760000' AS DateTime2), N'0383552922', NULL, 1918, 1, N'note(admin-1): order delivery at June 27
                                                





', 3)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (13, N'Việt Nam', CAST(N'2020-06-24T09:13:23.4080000' AS DateTime2), N'0383552922', NULL, 398, 10004, N'Reason for canceling an order: Waiting for confirmation for too long
customer''s message: 
', 5)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (14, N'Việt Nam', CAST(N'2020-06-24T10:09:47.2510000' AS DateTime2), N'0383552922', NULL, 2997, 1, N'
', 2)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (15, N'Việt Nam', CAST(N'2020-06-24T10:20:38.7510000' AS DateTime2), N'0383552922', NULL, 10, 1, NULL, 1)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (16, N'Việt Nam', CAST(N'2020-06-24T10:35:36.4990000' AS DateTime2), N'0383552922', NULL, 5891, 1, N'note(admin-1): order delivery at 2pm june 27
                                                
', 3)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (17, N'Việt Nam', CAST(N'2020-06-27T23:38:36.8690000' AS DateTime2), N'0383552922', NULL, 943, 10004, NULL, 1)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (18, N'Việt Nam', CAST(N'2020-06-27T23:42:49.3280000' AS DateTime2), N'0383552922', NULL, 1193, 10004, NULL, 1)
INSERT [dbo].[bill] ([id], [address], [order_date], [phone], [received_date], [total_money], [customer_id], [description], [status_id]) VALUES (19, N'Việt Nam', CAST(N'2020-06-27T23:43:46.7120000' AS DateTime2), N'0383552922', NULL, 1011, 10004, NULL, 1)
SET IDENTITY_INSERT [dbo].[bill] OFF
SET IDENTITY_INSERT [dbo].[bill_detai] ON 

INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (20, 199, 1, 8, 15)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (21, 1899, 1, 8, 16)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (22, 775, 3, NULL, 19)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (23, 15, 1, 9, 32)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (24, 1899, 1, 9, 16)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (30, 19, 1, NULL, 35)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (31, 1899, 1, 12, 16)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (32, 199, 2, 13, 15)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (33, 10, 1, 13, 24)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (34, 999, 3, 14, 17)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (35, 10, 1, 15, 24)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (36, 199, 1, 16, 15)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (37, 1899, 3, 16, 16)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (38, 10, 3, 17, 24)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (39, 913, 1, 17, 18)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (40, 199, 1, 18, 15)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (41, 999, 1, 18, 17)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (42, 12, 1, 19, 34)
INSERT [dbo].[bill_detai] ([id], [price], [quantity], [bill_id], [productid]) VALUES (43, 999, 1, 19, 17)
SET IDENTITY_INSERT [dbo].[bill_detai] OFF
INSERT [dbo].[bill_status] ([id], [name]) VALUES (1, N'wait for confirmation')
INSERT [dbo].[bill_status] ([id], [name]) VALUES (2, N'Confirmed')
INSERT [dbo].[bill_status] ([id], [name]) VALUES (3, N'On delivery')
INSERT [dbo].[bill_status] ([id], [name]) VALUES (4, N'Successfully')
INSERT [dbo].[bill_status] ([id], [name]) VALUES (5, N'Cancel')
INSERT [dbo].[bill_status] ([id], [name]) VALUES (6, N'Delivery failed')
INSERT [dbo].[category] ([category_id], [category_name]) VALUES (1, N'Boy')
INSERT [dbo].[category] ([category_id], [category_name]) VALUES (2, N'Girl')
INSERT [dbo].[category] ([category_id], [category_name]) VALUES (14, N'sport')
INSERT [dbo].[category] ([category_id], [category_name]) VALUES (33, N'kids')
SET IDENTITY_INSERT [dbo].[customer] ON 

INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (1, N'Việt Nam', CAST(N'2018-06-14T00:00:00.0000000' AS DateTime2), N'Lưu Quỳnh Dư', N'luquynhdu@gmail.com', N'12345678', N'0383552922', 1)
INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (10002, N'Việt Nam', NULL, N'Văn Đại Nguyễn', N'nguyenvandai2998@gmail.com', N'12345678', N'0383552965', 0)
INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (10003, N'Việt Nam             ', NULL, N'nguyễn thành đồng', N'nguyenthanh@gmail.com', N'12345678', N'0916089813', 1)
INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (10004, N'Việt Nam', CAST(N'2000-08-17T00:00:00.0000000' AS DateTime2), N'nguyễn thị phương nhật', N'nhatntp@gmail.com', N'12345678', N'0383552922', 1)
INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (10005, N'                                ', NULL, N'Nguyễn Huy Hoàng', N'huyhoang@gmail.com', N'12345678', N'', 1)
INSERT [dbo].[customer] ([customer_id], [address], [birthday], [customer_name], [email], [password], [phone], [status]) VALUES (10007, N'                                ', NULL, N'', N'', N'12345678', N'', 1)
SET IDENTITY_INSERT [dbo].[customer] OFF
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (39)
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (39)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (7, N's-p1.jpg', 23, N'ADDIDAS NEW HAMMER SOLE FOR SPORTS ', 99, 0, 1, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 10)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (15, N's-p1.jpg', 199, N'ADDIDAS NEW THOR SOLE FOR MAN', 998, 1, 1, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N'p6.jpg', N'p4.jpg', 5)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (16, N'p2.jpg', 1899, N'BALANCIAGA REVENDER DIO EXPESSION', 994, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (17, N'p4.jpg', 999, N'BALANCIAGA REVENDER DIO VIP', 28, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (18, N'p6.jpg', 913, N'GUCCI FEMALE SEXY AVENLAGON TE', 13, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (19, N'p2.jpg', 775, N'GUCCI MAN PERFOMERCE TE DEIRVON', 0, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 8.8)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (24, N'p6.jpg', 10, N'GA NEW THOR SOLE FOR MAN', 11, 1, 1, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (27, N'p7.jpg', 10, N'GUCCI SNAKE RED PERFORMJ', 10, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (31, N'p6.jpg', 8, N'ADDIDAS NEW DONELISOLE KINGQ', 99, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (32, N'p2.jpg', 15, N'LA''s DEFORMER VUITON 23', 11, 1, 2, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (34, N'p7.jpg', 12, N'KING SPOT LARDE''S STORM', 5, 1, 14, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (35, N'p5.jpg', 19, N'ADDIDAS NEW HAMMER SOLE FOR SPORTS PERSON NEW A', 0, 1, 33, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 1)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (36, N'p6.jpg', 12, N'Adidas Falcon Cream Orange Sneakers White Size 36 2/3', 9, 1, 1, N'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites', N's-p1.jpg', N'p4.jpg', 0)
INSERT [dbo].[product] ([productid], [image], [price], [product_name], [quantity], [status], [category_id], [description], [image1], [image2], [sale]) VALUES (37, N'p6.jpg', 22, N'DIO BANLANCE ROUNTER 9000', 27, 1, 1, N'GOOD', N's-p1.jpg', N'p5.jpg', 9)
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_c0r9atamxvbhjjvy5j8da1kam]    Script Date: 06/07/2020 4:25:16 CH ******/
ALTER TABLE [dbo].[admin] ADD  CONSTRAINT [UK_c0r9atamxvbhjjvy5j8da1kam] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_dwk6cx0afu8bs9o4t536v1j5v]    Script Date: 06/07/2020 4:25:16 CH ******/
ALTER TABLE [dbo].[customer] ADD  CONSTRAINT [UK_dwk6cx0afu8bs9o4t536v1j5v] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [FK_BILL_CUSTOMER] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([customer_id])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [FK_BILL_CUSTOMER]
GO
ALTER TABLE [dbo].[bill]  WITH CHECK ADD  CONSTRAINT [FKaqoqfbomovlbk3fb6bgrxfqe8] FOREIGN KEY([status_id])
REFERENCES [dbo].[bill_status] ([id])
GO
ALTER TABLE [dbo].[bill] CHECK CONSTRAINT [FKaqoqfbomovlbk3fb6bgrxfqe8]
GO
ALTER TABLE [dbo].[bill_detai]  WITH CHECK ADD  CONSTRAINT [FK_BILL_BILLDETAIL] FOREIGN KEY([bill_id])
REFERENCES [dbo].[bill] ([id])
GO
ALTER TABLE [dbo].[bill_detai] CHECK CONSTRAINT [FK_BILL_BILLDETAIL]
GO
ALTER TABLE [dbo].[bill_detai]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_BILLDETAIL] FOREIGN KEY([productid])
REFERENCES [dbo].[product] ([productid])
GO
ALTER TABLE [dbo].[bill_detai] CHECK CONSTRAINT [FK_PRODUCT_BILLDETAIL]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FK_CART_CUSTOMER] FOREIGN KEY([customer_id])
REFERENCES [dbo].[customer] ([customer_id])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FK_CART_CUSTOMER]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_CART] FOREIGN KEY([product_id])
REFERENCES [dbo].[product] ([productid])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FK_PRODUCT_CART]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK_PRODUCT_CATEGORY] FOREIGN KEY([category_id])
REFERENCES [dbo].[category] ([category_id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK_PRODUCT_CATEGORY]

