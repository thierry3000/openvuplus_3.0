SUMMARY = "lightweight DLNA/UPnP-AV server targeted at embedded systems"
HOMEPAGE = "http://sourceforge.net/projects/minidlna/"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b1a795ac1a06805cf8fd74920bc46b5c"
DEPENDS = "libexif jpeg libid3tag flac libvorbis sqlite3 libav util-linux"
SRCDATE = "20120408"
PV = "1.0.99+cvs${SRCDATE}"
PR = "r4"

SRC_URI = "cvs://anonymous@minidlna.cvs.sourceforge.net/cvsroot/minidlna;module=minidlna \
		file://default_sqlite_caches.diff \
		file://minidlna.conf \
"

S = "${WORKDIR}/${PN}"

do_configure_prepend() {
        sed -i 's/AM_INIT_AUTOMAKE.*$/AM_INIT_AUTOMAKE([foreign subdir-objects])/' ${S}/configure.ac
}

inherit autotools-brokensep gettext

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}/test*"

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

do_install_append() {
	install -d ${D}${sysconfdir}
	install -m 644 minidlna.conf ${D}${sysconfdir}
}
