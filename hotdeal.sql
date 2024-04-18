USE [master]
GO
/****** Object:  Database [HotDeal]    Script Date: 4/19/2024 12:23:15 AM ******/
CREATE DATABASE [HotDeal]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotDeal', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\HotDeal.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HotDeal_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\HotDeal_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [HotDeal] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotDeal].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotDeal] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotDeal] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotDeal] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotDeal] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotDeal] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotDeal] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotDeal] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotDeal] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotDeal] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotDeal] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotDeal] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotDeal] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotDeal] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotDeal] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotDeal] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotDeal] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotDeal] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotDeal] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotDeal] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotDeal] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotDeal] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotDeal] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotDeal] SET RECOVERY FULL 
GO
ALTER DATABASE [HotDeal] SET  MULTI_USER 
GO
ALTER DATABASE [HotDeal] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotDeal] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotDeal] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotDeal] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HotDeal] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [HotDeal] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'HotDeal', N'ON'
GO
ALTER DATABASE [HotDeal] SET QUERY_STORE = ON
GO
ALTER DATABASE [HotDeal] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [HotDeal]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[id] [int] NOT NULL,
	[quantity] [int] NULL,
	[created_at] [datetime] NULL,
	[total_price] [decimal](18, 0) NULL,
	[user_id] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[id] [int] NOT NULL,
	[quantity] [int] NULL,
	[price] [decimal](18, 0) NULL,
	[product_id] [int] NULL,
	[order_id] [int] NULL,
	[created_at] [datetime] NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] NOT NULL,
	[name] [nvarchar](50) NULL,
	[desciption] [nvarchar](50) NULL,
	[price] [decimal](18, 0) NULL,
	[discount_percent] [decimal](18, 0) NULL,
	[expiration] [datetime] NULL,
	[category_id] [int] NULL,
	[created_at] [datetime] NULL,
	[inventory] [int] NULL,
	[is_actived] [bit] NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] NOT NULL,
	[role_name] [varchar](50) NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] NOT NULL,
	[username] [varchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[first_name] [varchar](50) NULL,
	[last_name] [varchar](50) NULL,
	[telephone] [varchar](50) NULL,
	[created_at] [datetime] NULL,
	[last_loginDate] [datetime] NULL,
	[modified_at] [datetime] NULL,
	[role_id] [int] NULL,
	[is_actived] [bit] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Voucher]    Script Date: 4/19/2024 12:23:15 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voucher](
	[id] [int] NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](50) NULL,
	[price] [decimal](18, 0) NULL,
	[expiration] [datetime] NULL,
	[created_at] [datetime] NULL,
	[quantity] [int] NULL,
	[use_left] [int] NULL,
	[is_actived] [bit] NULL,
	[voucher_type] [nvarchar](50) NULL,
	[order_id] [int] NULL
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_User] FOREIGN KEY([user_id])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_User]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([product_id])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([category_id])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([role_id])
REFERENCES [dbo].[Role] ([role_id])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
ALTER TABLE [dbo].[Voucher]  WITH CHECK ADD  CONSTRAINT [FK_Voucher_Order] FOREIGN KEY([order_id])
REFERENCES [dbo].[Order] ([id])
GO
ALTER TABLE [dbo].[Voucher] CHECK CONSTRAINT [FK_Voucher_Order]
GO
USE [master]
GO
ALTER DATABASE [HotDeal] SET  READ_WRITE 
GO
