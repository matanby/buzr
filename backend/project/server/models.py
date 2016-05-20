from project.server import db


class Location(db.Model):
    __tablename__ = "locations"

    id = db.Column(db.String(255), primary_key=True)
    description = db.Column(db.String(255), unique=False, nullable=False)
    map_photo_url = db.Column(db.String(255), unique=False, nullable=True)

    @property
    def json(self):
        return {
            'id': self.id,
            'description': self.description,
            'map_photo_url': self.map_photo_url,
        }


class Category(db.Model):
    __tablename__ = "categories"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(255), unique=False, nullable=False)
    icon_url = db.Column(db.String, unique=False, nullable=False)

    @property
    def json(self):
        return {
            'id': self.id,
            'name': self.name,
            'icon_url': self.icon_url,
        }


class Store(db.Model):
    __tablename__ = "stores"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(255), unique=True, nullable=False)
    address = db.Column(db.String(255), unique=False, nullable=False)
    location_id = db.Column(db.String, db.ForeignKey(Location.id), unique=False, nullable=False)
    category_id = db.Column(db.Integer, db.ForeignKey(Category.id), unique=False, nullable=False)
    logo_url = db.Column(db.String, unique=False, nullable=False)
    photo_url = db.Column(db.String, unique=False, nullable=False)

    location = db.relationship('Location')
    category = db.relationship('Category')

    @property
    def json(self):
        return {
            'id': self.id,
            'name': self.name,
            'address': self.address,
            'location_id': self.location_id,
            'category_id': self.category_id,
            'logo_url': self.logo_url,
            'photo_url': self.photo_url,
        }


class Deal(db.Model):
    __tablename__ = "deals"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    store_id = db.Column(db.Integer, db.ForeignKey(Store.id), unique=False, nullable=False)
    description = db.Column(db.String(255), unique=False, nullable=False)
    start_time = db.Column(db.INTEGER, unique=False, nullable=False)
    end_time = db.Column(db.INTEGER, unique=False, nullable=False)
    photo_url = db.Column(db.String, unique=False, nullable=False)
    large_photo_url = db.Column(db.String, unique=False, nullable=False)

    store = db.relationship('Store')

    @property
    def json(self):
        return {
            'id': self.id,
            'store_id': self.store_id,
            'description': self.description,
            'start_time': self.start_time,
            'end_time': self.end_time,
            'photo_url': self.photo_url,
        }
