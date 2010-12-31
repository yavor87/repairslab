#! /bin/sh
[ ${JAVA_HOME} ] && JAVA=${JAVA_HOME}/bin/java || JAVA=java

# Are we running within Cygwin on some version of Windows?
cygwin=false;
case "`uname -s`" in
	CYGWIN*) cygwin=true ;;
esac

# ReparsLab home.
RL_HOME='/home/fabrizio/Scrivania/RepairsLab-1.0.4.0'

# ReparsLab home in Unix format.
if $cygwin ; then
	UNIX_STYLE_HOME=`cygpath "$RL_HOME"`
else
	UNIX_STYLE_HOME=$RL_HOME
fi

# First entry in classpath is the ReparsLab application.
TMP_CP=$UNIX_STYLE_HOME/gestRip.jar

# Then add all library jars to the classpath.
IFS=""
for a in $UNIX_STYLE_HOME/lib/*; do
	TMP_CP="$TMP_CP":"$a";
done

# Now add the system classpath to the classpath. If running
# Cygwin we also need to change the classpath to Windows format.
if $cygwin ; then
	TMP_CP=`cygpath -w -p $TMP_CP`
	TMP_CP=$TMP_CP';'$CLASSPATH
else
	TMP_CP=$TMP_CP:$CLASSPATH
fi

$JAVA -Xmx256m -cp $TMP_CP it.f2.gestRip.StartApp

